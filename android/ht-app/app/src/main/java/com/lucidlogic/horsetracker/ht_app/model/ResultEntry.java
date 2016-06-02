package com.lucidlogic.horsetracker.ht_app.model;

import com.parse.ParseClassName;

/**
 * Created by ian on 31/05/16.
 */
@ParseClassName("ResultEntry")
public class ResultEntry extends RaceEntry {

    public String getPosition(){
        return getString("position");
    }
}
