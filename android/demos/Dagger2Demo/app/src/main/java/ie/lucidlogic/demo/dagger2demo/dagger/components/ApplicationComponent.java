package ie.lucidlogic.demo.dagger2demo.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import ie.lucidlogic.demo.dagger2demo.dagger.modules.ApplicationModule;
import ie.lucidlogic.demo.dagger2demo.dagger.modules.DemoSubModule;
import ie.lucidlogic.demo.dagger2demo.dagger.subcomponents.SubComponent1;
import ie.lucidlogic.demo.dagger2demo.services.AuthService;

/**
 * Created by itowey on 20/07/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {


    AuthService authService();
    SubComponent1 subComponent1(DemoSubModule demoSubModule);
}
