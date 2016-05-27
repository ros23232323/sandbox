package com.lucidlogic.parse.parsedemo;

import android.content.Context;

import com.parse.Parse;

/**
 * Created by itowey on 27/05/16.
 */
public class ParseUtils {

    public static void initialize(Context context){

        Parse.initialize(new Parse.Configuration.Builder(context)
                .applicationId(context.getString(R.string.parse_application_id))
                .server(context.getString(R.string.parse_service_url)).build());
    }
}
