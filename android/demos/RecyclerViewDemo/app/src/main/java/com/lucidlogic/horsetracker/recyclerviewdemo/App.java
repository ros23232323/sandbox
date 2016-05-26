package com.lucidlogic.horsetracker.recyclerviewdemo;

import android.app.Application;
import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by itowey on 25/05/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO: init parse and crashlytics
        Log.d(this.getClass().getName(),"Init parse");
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));

    }
}
