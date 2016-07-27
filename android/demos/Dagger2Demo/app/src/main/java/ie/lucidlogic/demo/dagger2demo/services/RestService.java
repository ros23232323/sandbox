package ie.lucidlogic.demo.dagger2demo.services;

import javax.inject.Inject;

/**
 * Created by itowey on 20/07/16.
 */
public class RestService {
    @Inject
    public RestService(AuthService authService) {

    }
}
