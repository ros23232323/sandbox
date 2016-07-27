package ie.lucidlogic.demo.dagger2demo.dagger.subcomponents;

import dagger.Subcomponent;
import ie.lucidlogic.demo.dagger2demo.activities.MainActivity;
import ie.lucidlogic.demo.dagger2demo.dagger.modules.DemoSubModule;
import ie.lucidlogic.demo.dagger2demo.scopes.ActivityScope;
import ie.lucidlogic.demo.dagger2demo.services.SoapService;

/**
 * Created by itowey on 20/07/16.
 */
@ActivityScope
@Subcomponent(modules = {DemoSubModule.class})
public interface SubComponent1 {
    SoapService soapService();
    void inject(MainActivity mainActivity);

}
