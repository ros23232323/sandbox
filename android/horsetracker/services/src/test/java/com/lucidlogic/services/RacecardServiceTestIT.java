package com.lucidlogic.services;

import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidlogic.common.Racecard;
import com.lucidlogic.services.RacecardService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by ian on 16/04/16.
 */

public class RacecardServiceTestIT {

    private static final String TAG = RacecardServiceTestIT.class.getName();
    private RacecardService racecardService;

    @Before
    public void setUp(){

        Gson gson = new GsonBuilder().registerTypeAdapter(Racecard.class, new RacecardDeserializer()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://localhost:3000")
                .build();

        racecardService = retrofit.create(RacecardService.class);

    }

    @Test
    public void getRacecardsTests() {

        Observable<List<Racecard>> racecards = racecardService.getRacecards();

        racecards.subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate()).toBlocking()
                .subscribe(new Subscriber<List<Racecard>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("All done");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Racecard> racecards) {
                        for (Racecard racecard : racecards) {
                            System.out.println(
                                    racecard.getPageUrl() + "\t" + racecard.getDate() + "\t" + racecard.getMeetings().size());
                        }
                    }
                });
    }
}