package com.lucidlogic.horsetracker.ht_app;

import com.lucidlogic.horsetracker.ht_app.model.Entity;
import com.lucidlogic.horsetracker.ht_app.model.Meeting;
import com.lucidlogic.horsetracker.ht_app.model.Race;
import com.lucidlogic.horsetracker.ht_app.model.RaceEntry;
import com.lucidlogic.horsetracker.ht_app.model.Racecard;
import com.lucidlogic.horsetracker.ht_app.model.Result;
import com.lucidlogic.horsetracker.ht_app.model.ResultEntry;

import java.util.Date;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by itowey on 23/05/16.
 */
public interface RacingService {

  Single<Entity> getEntity(String id);

  Single<Meeting> getMeeting(String id);

  Single<Race> getRace(String id);

  Single<Racecard> getRacecard(Date id);

  Single<RaceEntry> getRaceEntry(String id);

  Single<Result> getResult(String id);

  Single<ResultEntry> getResultEntry(String id);
}
