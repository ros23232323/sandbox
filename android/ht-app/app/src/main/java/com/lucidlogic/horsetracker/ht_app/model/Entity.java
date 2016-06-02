package com.lucidlogic.horsetracker.ht_app.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Map;

/**
 * Created by ian on 31/05/16.
 */
@ParseClassName("Entity")
public class Entity extends ParseObject {

    public Entity() {
    }

    public String getDiscriminator(){
        return getString("discriminator");
    }
    public String getName(){
        return getString("name");
    }
    public String getProfileUrl(){
        return getString("profileUrl");
    }
    public Map<String, Object> getAttributes(){
        return getMap("attributes");
    }
}
