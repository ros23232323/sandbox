package com.lucidlogic.horsetracker.ht_app;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gson.Gson;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

/**
 * Created by itowey on 23/05/16.
 */
@RunWith(AndroidJUnit4.class)
public class RacecardServiceClientTest {

  Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

  RacecardServiceClient racecardServiceClient = new RacecardServiceClient(this.context);

  @Test
  public void racecardsHappyPath() {

    final RacingService racecardService = this.racecardServiceClient.getRacecardClient();
    final RacecardDTO racecardDTO = racecardService.getRacecard(this.context.getString(R.string.racecard_query))
        .toBlocking().value();
    final Gson gson = new Gson();

    System.out.println("Response " + gson.toJson(racecardDTO));
  }

  @Test
  public void raceHappyPath() {

    final RacingService racecardService = this.racecardServiceClient.getRacecardClient();
    final RaceDTO race = racecardService.getRace(this.context.getString(R.string.race_query)).toBlocking().value();
    final Gson gson = new Gson();

    System.out.println("Response " + gson.toJson(race));
  }
}
