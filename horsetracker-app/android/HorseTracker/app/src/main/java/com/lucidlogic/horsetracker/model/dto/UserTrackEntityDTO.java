package com.lucidlogic.horsetracker.model.dto;

import com.lucidlogic.horsetracker.config.Constants;
import com.lucidlogic.horsetracker.model.Entity;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ian on 23/07/16.
 */
@ParseClassName("UserTrackEntity")
public class UserTrackEntityDTO extends ParseObject{

    public ParseUser getUser(){
        return getParseUser(Constants.USER);
    }
    public EntityDTO getEntityDTO(){
        return (EntityDTO)getParseObject(Constants.ENTITY);
    }

    public void setUser(ParseUser user){
        put(Constants.NAME, user);
    }
    public void setEntityDTO(EntityDTO entity){
        put(Constants.ENTITY, entity);
    }

}
