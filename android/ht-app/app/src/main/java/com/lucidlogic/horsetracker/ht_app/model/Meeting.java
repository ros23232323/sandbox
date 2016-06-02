package com.lucidlogic.horsetracker.ht_app.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;
import java.util.Map;

/**
 * Created by ian on 31/05/16.
 */
@ParseClassName("Meeting")
public class Meeting extends ParseObject {

    public String getName(){
        return getString("name");
    }
    public String getGoing(){
        return getString("going");
    }

    public String getSurface(){
        return getString("surface");
    }

    public List<Race> getRaces(){
        return getList("races");
    }

}
