package com.lucidlogic.horsetracker.model;

import com.lucidlogic.horsetracker.config.Constants;
import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ian on 06/07/16.
 */
@ParseClassName(Constants.RACE)
public class RaceDTO extends ParseObject{

    public String getName(){
        return getString(Constants.RACE_NAME);
    }

    public String getTime(){
        return getString(Constants.RACE_TIME);
    }

    public String getAbandoned(){
        return getString(Constants.ABANDONED);
    }

    public void getName(String name ){
        put(Constants.RACE_NAME, name);
    }

    public void getTime(String time){
        put(Constants.RACE_TIME, time);
    }

    public void getAbandoned(String abandoned){
        put(Constants.ABANDONED, abandoned);
    }
}
