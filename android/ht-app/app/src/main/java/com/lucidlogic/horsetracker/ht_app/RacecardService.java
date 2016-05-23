package com.lucidlogic.horsetracker.ht_app;

import retrofit2.http.GET;
import rx.Single;

/**
 * Created by itowey on 23/05/16.
 */
public interface RacecardService {

    @GET("/query/service?statement=select%20h.*%20from`horsetracker-test`%20h%20where%20h._type='Racecard'")
    Single<RacecardResponse> getRacecard(/*@Query("statement")final String nq1lStatement*/);


}
