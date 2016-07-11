package com.lucidlogic.horsetracker.model;

import android.databinding.BaseObservable;

/**
 * Created by ian on 06/07/16.
 */
public class Race extends BaseObservable {

    private String abandoned;
    private String name;
    private String time;

    public void setAbandoned(String abandoned) {
        this.abandoned = abandoned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAbandoned() {
        return abandoned;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
