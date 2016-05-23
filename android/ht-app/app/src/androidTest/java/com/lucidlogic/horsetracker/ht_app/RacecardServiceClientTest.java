package com.lucidlogic.horsetracker.ht_app;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by itowey on 23/05/16.
 */
@RunWith(AndroidJUnit4.class)
public class RacecardServiceClientTest {

    RacecardServiceClient racecardServiceClient = new RacecardServiceClient(InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext());

    @Test
    public void racecardsHappyPath(){
        RacecardService racecardService = racecardServiceClient.getRacecardClient();
        RacecardResponse racecardResponse = racecardService.getRacecard(/*"select h.* from`horsetracker-test` h where h._type='Racecard'"*/).toBlocking().value();
        Gson gson = new Gson();
        System.out.println("Response " + gson.toJson(racecardResponse));
    }
}
