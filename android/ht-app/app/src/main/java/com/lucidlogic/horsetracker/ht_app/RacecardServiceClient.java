package com.lucidlogic.horsetracker.ht_app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by itowey on 23/05/16.
 */
public class RacecardServiceClient {

  private final RacingService racingService;

  public RacecardServiceClient(final Context context) {
    final OkHttpClient okHttpClient = new OkHttpClient();
    final Gson gson = new GsonBuilder().registerTypeAdapter(RacecardDTO.class, new RacecardDTOJsonDeserializer())
        .registerTypeAdapter(RaceDTO.class, new RaceDTOJsonDeserializer()).create();

    final Retrofit retrofit = new Retrofit.Builder().baseUrl(context.getString(R.string.racecard_base_url))
        .client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    this.racingService = retrofit.create(RacingService.class);

  }

  public RacingService getRacecardClient() {
    return this.racingService;
  }
}
