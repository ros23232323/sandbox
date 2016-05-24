package com.lucidlogic.horsetracker.ht_app;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by itowey on 23/05/16.
 */
public interface RacingService {

  @GET("/query/service")
  Single<RacecardDTO> getRacecard(@Query("statement")
  final String nq1lStatement);

  @GET("/query/service")
  Single<RaceDTO> getRace(@Query("statement")
  final String nq1lStatement);
}
