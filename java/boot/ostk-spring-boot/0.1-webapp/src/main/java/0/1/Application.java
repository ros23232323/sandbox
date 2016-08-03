package 0.1;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.overstock.framework.spring.xmlconfig.OverstockSpringCloudInitializer;

@SpringBootApplication
@EnableEurekaClient
public class Application {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.addInitializers(new OverstockSpringCloudInitializer());
    app.run(args);
  }
}
