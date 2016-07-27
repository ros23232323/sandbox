package ie.lucidlogic.demo.dagger2demo.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import ie.lucidlogic.demo.dagger2demo.App;
import ie.lucidlogic.demo.dagger2demo.R;
import ie.lucidlogic.demo.dagger2demo.dagger.modules.DemoSubModule;
import ie.lucidlogic.demo.dagger2demo.dagger.subcomponents.SubComponent1;
import ie.lucidlogic.demo.dagger2demo.services.AuthService;
import ie.lucidlogic.demo.dagger2demo.services.RestService;
import ie.lucidlogic.demo.dagger2demo.utils.CheckUtils;

public class MainActivity extends AppCompatActivity {

    @Inject
    AuthService authService;

    @Inject
    RestService restService;

    @Inject
    SubComponent1 subComponent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App)getApplication()).getApplicationComponent().subComponent1(new DemoSubModule()).inject(this);
        setContentView(R.layout.activity_main);
        CheckUtils.checkNullAndFail(restService);
        CheckUtils.checkNullAndFail(subComponent1);
        CheckUtils.checkNullAndFail(subComponent1.soapService());
        CheckUtils.checkNullAndFail(authService);
        Log.i("","");
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View v = super.onCreateView(name, context, attrs);
        return v;
    }
}
