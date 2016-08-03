package 0.1.web.rest;

import static com.jayway.restassured.RestAssured.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;

import 0.1.Application;

import 0.1.service.GreetingsService;
import 0.1.service.GreetingsServiceImpl;
import 0.1.service.HealthCheckService;
import com.overstock.framework.spring.xmlconfig.OverstockSpringCloudInitializer;

/**
 * This is Boot Test that starts an actual container and allows you to 
 * test your rest end points. Note that we this is Mocking out Service level code.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, ControllerTest.MockConfig.class}, initializers = {
    OverstockSpringCloudInitializer.class})
@WebIntegrationTest(value = { "eureka.client.enabled=false" }, randomPort = true)
public class ControllerTest {
  
  @Configuration
  static class MockConfig {
    @Bean
    public HealthCheckService healthCheckService() {
      return mock(HealthCheckService.class);
    }
    
    @Bean
    public GreetingsService greetingService() {
      return mock(GreetingsService.class);
    }
  }
  
  @Value("${local.server.port}")
  int port;

  @Before
  public void setUp() {
    RestAssured.port = port;
  }
  
  @Inject
  private GreetingsService greetingService;
  
  @Test
  public void greeting() {
    when(greetingService.getGreeting()).thenReturn(GreetingsServiceImpl.GREETING);
    when().get("/greeting").then().statusCode(HttpStatus.OK.value()).body("greeting", Matchers.is(GreetingsServiceImpl.GREETING));
  }
  
  @Inject
  private HealthCheckService healthCheckService;
  
  @Test
  public void health() {
    when(healthCheckService.isHealthy()).thenReturn(Boolean.TRUE);
    when().head("/health").then().statusCode(HttpStatus.OK.value());
    reset(healthCheckService);
    when(healthCheckService.isHealthy()).thenReturn(Boolean.FALSE);
    when().head("/health").then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
  }
}
