package ie.lucidlogic.demo.dagger2demo.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ie.lucidlogic.demo.dagger2demo.services.AuthService;
import ie.lucidlogic.demo.dagger2demo.services.DataService;
import ie.lucidlogic.demo.dagger2demo.services.JmsService;
import ie.lucidlogic.demo.dagger2demo.services.RestService;

/**
 * Created by itowey on 20/07/16.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public AuthService providesAuthService(){
        return new AuthService();
    }

    @Provides
    @Singleton
    public DataService providesDataService(AuthService authService){
        return new DataService(authService);
    }

    @Provides
    @Singleton
    public RestService providesRestService(AuthService authService){
        return new RestService(authService);
    }

    @Provides
    @Singleton
    public JmsService providesJmsService(){
        return new JmsService();
    }

}
