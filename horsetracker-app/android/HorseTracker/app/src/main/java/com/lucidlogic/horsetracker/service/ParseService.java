package com.lucidlogic.horsetracker.service;

import com.lucidlogic.horsetracker.model.dto.RaceDTO;
import com.lucidlogic.horsetracker.model.dto.RacecardDTO;
import com.parse.ParseException;
import com.parse.ParseObject;
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

    public static Observable<RaceDTO> getRace(String raceObjectId) {

        return ParseObservable.find(
                ParseQuery.getQuery(RaceDTO.class)
                .whereEqualTo("objectId", raceObjectId)
                .include("runners")
                .include("runners.jockey")
                .include("runners.horse")
                .include("runners.trainer")
                );
    }

    public static void putInLocalParseStore(ParseObject parseObject){
        parseObject.pinInBackground();
    }

    public static <T extends ParseObject> T getFromLocalParseStore(String objectId, Class<T> clazz){
        try {
            return ParseQuery.getQuery(clazz).fromLocalDatastore().get(objectId);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
