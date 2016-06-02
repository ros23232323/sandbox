package com.lucidlogic.horsetracker.ht_app.service;

import android.content.Context;

import com.lucidlogic.horsetracker.ht_app.R;
import com.parse.Parse;

/**
 * Created by ian on 31/05/16.
 */
public class ParseService {

    public static void init(Context context){
        Parse.initialize(
         new Parse.Configuration.Builder(context)
                 .applicationId(context.getString(R.string.parse_app_id))
                 .server(context.getString(R.string.parse_url))
                 .build()
        );
    }
}
