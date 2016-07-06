package com.lucidlogic.horsetracker.model;

import com.lucidlogic.horsetracker.config.Constants;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

/**
 * Created by ian on 06/07/16.
 */
@ParseClassName(Constants.RACECARD)
public class RacecardParse extends ParseObject{

    public String getDate(){ return getString(Constants.DATE); }
    public void  setDate(Date date){ put(Constants.DATE, date); }



}
