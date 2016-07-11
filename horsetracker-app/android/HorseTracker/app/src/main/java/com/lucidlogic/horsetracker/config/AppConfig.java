package com.lucidlogic.horsetracker.config;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.lucidlogic.horsetracker.BuildConfig;
import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.logging.ReleaseTree;
import com.lucidlogic.horsetracker.model.MeetingDTO;
import com.lucidlogic.horsetracker.model.RaceDTO;
import com.lucidlogic.horsetracker.model.RacecardDTO;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseNetworkInterceptor;

import java.io.IOException;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by ian on 06/07/16.
 */
public class AppConfig {

    public static void init(Context context){

        //Parse Init
        ParseObject.registerSubclass(RaceDTO.class);
        ParseObject.registerSubclass(MeetingDTO.class);
        ParseObject.registerSubclass(RacecardDTO.class);
        Parse.enableLocalDatastore(context);

        Parse.initialize(
                new Parse.Configuration.Builder(context)
                        .applicationId(context.getString(R.string.parse_app_id))
//                        .addNetworkInterceptor(new ParseNetworkInterceptor() {
//                            @Override
//                            public ParseHttpResponse intercept(Chain chain) throws IOException {
//                                return chain.proceed(chain.getRequest());
//                            }
//                        })
                        .server(context.getString(R.string.parse_url))
                        .build()
        );


        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
            //Crashlytics
            Fabric.with(context, new Crashlytics());
        }

    }
}
