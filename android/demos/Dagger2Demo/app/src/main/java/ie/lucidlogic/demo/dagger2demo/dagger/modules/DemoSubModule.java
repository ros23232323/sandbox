package ie.lucidlogic.demo.dagger2demo.dagger.modules;

import dagger.Module;
import dagger.Provides;
import ie.lucidlogic.demo.dagger2demo.scopes.ActivityScope;
import ie.lucidlogic.demo.dagger2demo.services.SoapService;

/**
 * Created by itowey on 20/07/16.
 */
@Module
public class DemoSubModule {

    @Provides @ActivityScope
    public SoapService providesSoapService(){
        return new SoapService();
    }

}
