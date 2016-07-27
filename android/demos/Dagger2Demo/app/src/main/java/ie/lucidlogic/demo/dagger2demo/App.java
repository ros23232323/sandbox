package ie.lucidlogic.demo.dagger2demo;

import android.app.Application;

import ie.lucidlogic.demo.dagger2demo.dagger.components.ApplicationComponent;
import ie.lucidlogic.demo.dagger2demo.dagger.components.DaggerApplicationComponent;
import ie.lucidlogic.demo.dagger2demo.utils.Constants;
import mortar.MortarScope;


/**
 * Created by itowey on 20/07/16.
 */
public class App extends Application {

    private MortarScope rootScope;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().build();
        rootScope = MortarScope.buildRootScope()
                .withService(Constants.DAGGER_SERVICE, applicationComponent)
                .build(Constants.MORTAR_ROOT);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public Object getSystemService(String name) {
        return rootScope.hasService(name) ?
                rootScope.getService(name) :
                super.getSystemService(name);
    }
}
