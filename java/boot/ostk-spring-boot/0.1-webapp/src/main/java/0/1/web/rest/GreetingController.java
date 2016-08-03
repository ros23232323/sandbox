package 0.1.web.rest;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import 0.1.common.Greeting;
import 0.1.service.GreetingsService;

@RestController
@RequestMapping(value="/greeting")
public class GreetingController {
  private final GreetingsService greetingService;
  
  @Inject
  public GreetingController(GreetingsService greetingService) {
    this.greetingService = greetingService;    
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Greeting get() {
    return new Greeting(greetingService.getGreeting());
  }
}
