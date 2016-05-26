package com.lucidlogic.horsetracker.recyclerviewdemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itowey on 25/05/16.
 */
public class Card {

    private String track;

    private String trackGoing;

    private String trackSurface;

    private String trackUrl;

    private List<Race> races = new ArrayList<>();

    public String getTrack() {
        return this.track;
    }

    public void setTrack(final String track) {
        this.track = track;
    }

    public String getTrackGoing() {
        return this.trackGoing;
    }

    public void setTrackGoing(final String trackGoing) {
        this.trackGoing = trackGoing;
    }

    public String getTrackSurface() {
        return this.trackSurface;
    }

    public void setTrackSurface(final String trackSurface) {
        this.trackSurface = trackSurface;
    }

    public String getTrackUrl() {
        return this.trackUrl;
    }

    public void setTrackUrl(final String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public List<Race> getRaces() {
        return this.races;
    }

    public void setRaces(final List<Race> races) {
        this.races = races;
    }

}
