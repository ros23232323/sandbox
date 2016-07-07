package com.lucidlogic.fixtureui.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by ian on 15/06/16.
 */
@ParseClassName("UserTrackableEntity")
public class UserTrackableEntityParse extends ParseObject{

    public static final String _USER = "type";
    public static final String _TRACKABLE_ENTITY = "trackableEntity";

    public ParseUser getUser(){
        return getParseUser(_USER);
    }

    public TrackableEntityParse getTrackableEntity(){
        return (TrackableEntityParse) getParseObject(_TRACKABLE_ENTITY);
    }

    public void setUser(ParseUser user){
        put(_USER, user);
    }

    public void setTrackableEntity(TrackableEntityParse trackableEntityParse){
        put(_TRACKABLE_ENTITY, trackableEntityParse);
    }

}
