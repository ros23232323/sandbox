package 0.1.integration;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.annotation.Configuration;

import com.overstock.shadow.retrofit.spring.EnableRetrofitShadow;
import com.overstock.framework.spring.xmlconfig.OverstockSpringCloudInitializer;

import 0.1.Application;
import 0.1.client.GreetingServiceClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {IntegrationTest.ITConfig.class,
    Application.class}, initializers = {OverstockSpringCloudInitializer.class})
@WebIntegrationTest(value = { "eureka.client.enabled=false", // Don't start Eureka
    "0.1.client.dynamicServiceDiscoveryEnabled=false" // No dynamicServiceDiscovery
}, randomPort = false)
public class IntegrationTest {
  @Configuration
  @EnableRetrofitShadow
  static class ITConfig {}

  @Inject
  private GreetingServiceClient greetingServiceClient;

  @Test
  public void integration() {
    assertNotNull(greetingServiceClient.greeting());   
  }
}
