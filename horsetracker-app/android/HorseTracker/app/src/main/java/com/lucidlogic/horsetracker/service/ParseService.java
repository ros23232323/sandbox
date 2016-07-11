package com.lucidlogic.horsetracker.service;

import android.text.format.DateUtils;

import com.lucidlogic.horsetracker.config.Constants;
import com.lucidlogic.horsetracker.model.RacecardDTO;
import com.parse.ParseQuery;

import java.util.Calendar;
import java.util.Date;

import rx.Observable;
import rx.parse.ParseObservable;

/**
 * Created by ian on 10/07/16.
 */
public class ParseService {

    public static Observable<RacecardDTO> getRacecards(Date raceDate){

        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(raceDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time = cal.getTimeInMillis();
        return ParseObservable.find(ParseQuery.getQuery(RacecardDTO.class)
                .include("meetings")
//                .whereEqualTo(Constants.DATE, cal.getTime())
                .include("meetings.races")
                .addAscendingOrder("date"))
                .first();
    }
}
