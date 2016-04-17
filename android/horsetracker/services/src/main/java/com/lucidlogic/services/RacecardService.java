package com.lucidlogic.services;

import com.lucidlogic.common.Racecard;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by ian on 16/04/16.
 */
public interface RacecardService {

    @GET("racecard")
    Observable<List<Racecard>> getRacecards();
}
