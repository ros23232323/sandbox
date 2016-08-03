package 0.1.service;

/**
 * Service that sits behind the webapp. Do NOT let presentation logic and presentation components bleed into the service.
 */
public interface GreetingsService {
  String getGreeting();
}
