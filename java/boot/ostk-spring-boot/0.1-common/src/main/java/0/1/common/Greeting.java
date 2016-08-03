package 0.1.common;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@AutoProperty
public class Greeting {
  /**
   * The Greeting element
   */
  private String greeting;

  public Greeting() {
    setGreeting("");
  }

  public Greeting(String theGreeting) {
    setGreeting(theGreeting);
  }

  /**
   * @return the Greeting element
   */
  public String getGreeting() {
    return greeting;
  }

  /**
   * @param theGreeting the Greeting to set
   */
  public void setGreeting(String theGreeting) {
    greeting = theGreeting;
  }

  @Override
  public boolean equals(Object aGreeting) {
    return Pojomatic.equals(this, aGreeting);
  }

  @Override
  public int hashCode() {
    return getGreeting().hashCode();
  }

  @Override
  public String toString() {
    return Pojomatic.toString(this);
  }
}
