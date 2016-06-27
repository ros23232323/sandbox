package com.lucidlogic.horsetracker.service;

import android.content.Context;
import android.util.Log;

import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.model.parse.MeetingParse;
import com.lucidlogic.horsetracker.model.parse.RaceParse;
import com.lucidlogic.horsetracker.model.parse.RacecardParse;
import com.lucidlogic.horsetracker.model.parse.TrackableEntityParse;
import com.lucidlogic.horsetracker.model.parse.UserTrackableEntityParse;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseNetworkInterceptor;

import java.io.IOException;

/**
 * Created by ian on 31/05/16.
 */
public class ParseService {

    public static final String TAG = ParseService.class.getName();

    public static void init(Context context){

        String appId = context.getString(R.string.parse_app_id);
        String serverUrl = context.getString(R.string.parse_server_url);
        String clientId = context.getString(R.string.parse_client_id);

        ParseObject.registerSubclass(RacecardParse.class);
        ParseObject.registerSubclass(MeetingParse.class);
        ParseObject.registerSubclass(RaceParse.class);
        ParseObject.registerSubclass(TrackableEntityParse.class);
        ParseObject.registerSubclass(UserTrackableEntityParse.class);
        Parse.enableLocalDatastore(context);
        Parse.initialize(
         new Parse.Configuration.Builder(context)
                     .applicationId(appId)
//                 .addNetworkInterceptor(new ParseNetworkInterceptor() {
//
//                     @Override
//                     public ParseHttpResponse intercept(Chain chain) throws IOException {
//                         ParseHttpRequest req = chain.getRequest();
//                         Log.i(TAG, req.getUrl());
//                         ParseHttpResponse resp = chain.proceed(req);
//                         Log.i(TAG, String.format("resp status code %d", resp.getStatusCode()));
//                         return resp;
//                     }
//                 })
                 .server(serverUrl)
                 .clientKey(clientId)
                 .build()
        );
    }
}
