package com.lucidlogic.horsetracker.service;

import com.lucidlogic.horsetracker.config.Constants;
import com.lucidlogic.horsetracker.model.RacecardDTO;
import com.parse.ParseQuery;

import java.util.Date;

import rx.Observable;
import rx.parse.ParseObservable;

/**
 * Created by ian on 10/07/16.
 */
public class ParseService {

    public static Observable<RacecardDTO> getRacecards(Date raceDate){
        return ParseObservable.find(ParseQuery.getQuery(RacecardDTO.class)
                .include("meetings")
                .whereEqualTo(Constants.DATE, raceDate)
                .include("meetings.races")
                .addAscendingOrder("date"))
                .first();
    }
}
