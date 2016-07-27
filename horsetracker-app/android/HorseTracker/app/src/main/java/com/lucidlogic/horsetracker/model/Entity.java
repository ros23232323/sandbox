package com.lucidlogic.horsetracker.model;

import android.databinding.BaseObservable;

import com.lucidlogic.horsetracker.config.Constants;
import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ian on 23/07/16.
 */
public class Entity extends BaseObservable{

    private String id;
    private String type;
    private String name;
    private String profileUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
