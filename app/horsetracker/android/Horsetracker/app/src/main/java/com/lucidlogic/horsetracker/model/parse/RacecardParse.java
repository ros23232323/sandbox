package com.lucidlogic.horsetracker.model.parse;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.List;

/**
 * Created by ian on 05/06/16.
 */
@ParseClassName("Racecard")
public class RacecardParse extends ParseObject {

    public RacecardParse() {
    }

    public Date getDate(){
        return getDate("date");
    }

    public List<MeetingParse> getMeetings(){
        return getList("meetings");
    }

    public void setDate(Date date){
        put("date", date);
    }

    public void setMeetings(List<MeetingParse> meetings){
        put("meetings", meetings);
    }



}
