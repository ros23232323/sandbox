package com.lucidlogic.horsetracker.ht_app.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ian on 31/05/16.
 */
@ParseClassName("Raceentry")
public class RaceEntry extends ParseObject {

    public RaceEntry() {
    }

    public Entity getHorse(){
        return (Entity)getParseObject("horse");
    }
    public Entity getJockey(){
        return (Entity)getParseObject("jockey");
    }
    public Entity getTrainer(){
        return (Entity)getParseObject("trainer");
    }

}
