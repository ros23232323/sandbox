package com.lucidlogic.horsetracker.ht_app;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by itowey on 23/05/16.
 */
public class RacecardServiceClient {

    private RacecardService racecardService;

    public RacecardServiceClient(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Gson gson = new GsonBuilder().registerTypeAdapter(RacecardResponse.class, new RacecardResponseJsonDeserializer())
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(context.getString(R.string.racecard_base_url))
                .client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        racecardService = retrofit.create(RacecardService.class);

    }

    public RacecardService getRacecardClient() {
        return racecardService;
    }
}
