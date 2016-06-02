package com.lucidlogic.horsetracker.ht_app.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * container for on days racing.
 */
@ParseClassName("Racecard")
public class Racecard extends ParseObject{

    public Racecard() {
    }

    public Date getDate(){
        return getDate("date");
    }
    public List<Meeting> getMeetings(){
        return getList("meetings");
    }
}
