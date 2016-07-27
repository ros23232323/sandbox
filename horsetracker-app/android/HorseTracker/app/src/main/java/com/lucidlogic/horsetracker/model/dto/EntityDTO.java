package com.lucidlogic.horsetracker.model.dto;

import com.lucidlogic.horsetracker.config.Constants;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by ian on 23/07/16.
 */
@ParseClassName("Entity")
public class EntityDTO extends ParseObject{

    public String getName(){
        return getString(Constants.NAME);
    }
    public String getProfileUrl(){
        return getString(Constants.PROFILE_URL);
    }
    public String getType(){
        return getString(Constants.TYPE);
    }

    public void getName(String name){
        put(Constants.NAME, name)
        ;
    }
    public void getProfileUrl(String  profileUrl){
        put(Constants.PROFILE_URL, profileUrl);
    }
    public void getType(String type){
        put(Constants.TYPE, type);
    }

}
