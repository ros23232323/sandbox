package com.lucidlogic.fixtureui.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Map;

/**
 * Created by ian on 15/06/16.
 */
@ParseClassName("TrackableEntity")
public class TrackableEntityParse extends ParseObject{

    public static final String _TYPE = "type";
    public static final String _NAME = "name";
    public static final String _PROFILE_URL = "profile_url";
    public static final String _ATTRIBUTES = "attributes";

    public String getType(){
        return getString(_TYPE);
    }

    public String getName(){
        return getString(_NAME);
    }

    public String getProfileUrl(){
        return getString(_PROFILE_URL);
    }

    public Map<String, Object> getAttributes(){
        return getMap(_ATTRIBUTES);
    }

    public void setType(String type){
        put(_TYPE, type);
    }

    public void setName(String  name){
        put(_NAME, name);
    }

    public void setProfileUrl(String  profileUrl){
        put(_PROFILE_URL, profileUrl);
    }

    public void setAttributes(Map<String, Object> map){
        put(_ATTRIBUTES, map);
    }
}
