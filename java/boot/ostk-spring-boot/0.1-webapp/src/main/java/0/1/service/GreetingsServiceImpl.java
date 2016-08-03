package 0.1.service;

import javax.inject.Named;

@Named
public class GreetingsServiceImpl implements GreetingsService {
  public static final String GREETING = "Hello World!";

  @Override
  public  String getGreeting() {
    return GREETING;
  }
}