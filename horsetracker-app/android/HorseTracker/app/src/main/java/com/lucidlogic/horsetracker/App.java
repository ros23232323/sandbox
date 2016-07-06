package com.lucidlogic.horsetracker;

import android.app.Application;

import com.lucidlogic.horsetracker.config.AppConfig;
import com.parse.Parse;

/**
 * Created by ian on 06/07/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConfig.init(this);
    }
}
