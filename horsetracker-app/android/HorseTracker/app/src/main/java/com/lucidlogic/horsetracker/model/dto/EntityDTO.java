package com.lucidlogic.horsetracker.model.dto;

import com.lucidlogic.horsetracker.config.Constants;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;
import java.util.Map;

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
    public Date getEntityDetailsCollectionDate(){ return getDate(Constants.ED_COLLECT_DATE); }
    public Map getEntityDetails(){
        return getMap(Constants.ENTITY_DETAILS);
    }

    public void setName(String name){ put(Constants.NAME, name) ; }
    public void setProfileUrl(String  profileUrl){
        put(Constants.PROFILE_URL, profileUrl);
    }
    public void setType(String type){
        put(Constants.TYPE, type);
    }
    public void setEntityDetailsCollectionDate(Date entityDetailsCollectionDate){ put(Constants.ED_COLLECT_DATE, entityDetailsCollectionDate); }
    public void setEntityDetails(Map entityDetails){ put(Constants.ENTITY_DETAILS, entityDetails); }

}
