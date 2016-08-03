package 0.1.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GreetingServiceTest {
  
  @Test
  public void greeting() {
    GreetingsServiceImpl impl = new GreetingsServiceImpl();
    assertEquals(GreetingsServiceImpl.GREETING, impl.getGreeting());
  }
}
