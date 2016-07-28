package ie.ll;

import org.springframework.boot.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import ie.ll.service.HelloWorldService;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class Example {

    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping("/")
    String home() {
        return "Hello World! " + this.helloWorldService.getHelloMessage();
    }

    @RequestMapping("/exception")
    String exception() {
        return this.helloWorldService.throwExceptionTest();
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Example.class, args);
    }
}
