package com.lucidlogic.horsetracker.config;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.lucidlogic.horsetracker.BuildConfig;
import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.logging.ReleaseTree;
import com.lucidlogic.horsetracker.model.MeetingParse;
import com.lucidlogic.horsetracker.model.Race;
import com.lucidlogic.horsetracker.model.RaceParse;
import com.lucidlogic.horsetracker.model.RacecardParse;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
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


        ParseObject.registerSubclass(RaceParse.class);
        ParseObject.registerSubclass(MeetingParse.class);
        ParseObject.registerSubclass(RacecardParse.class);
        Parse.enableLocalDatastore(context);

        Parse.initialize(
                new Parse.Configuration.Builder(context)
                        .applicationId(context.getString(R.string.parse_app_id))
                        .addNetworkInterceptor(new ParseNetworkInterceptor() {
                            @Override
                            public ParseHttpResponse intercept(Chain chain) throws IOException {
                                return chain.proceed(chain.getRequest());
                            }
                        })
                        .server(context.getString(R.string.parse_url))
                        .build()
        );

        Fabric.with(context, new Crashlytics());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new ReleaseTree());
        }

    }
}
