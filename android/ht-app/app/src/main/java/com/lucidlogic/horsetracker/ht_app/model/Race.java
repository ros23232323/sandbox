package com.lucidlogic.horsetracker.ht_app.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by ian on 31/05/16.
 */
@ParseClassName("Race")
public class Race extends ParseObject {

    public Race() {
    }

    public String getName(){
        return getString("name");
    }
    public String getClassification(){
        return getString("classification");
    }

    public List<RaceEntry> getRaceEntries(){
        return getList("raceEntries");
    }

    public Result getResult(){
        return (Result)getParseObject("result");
    }
}
