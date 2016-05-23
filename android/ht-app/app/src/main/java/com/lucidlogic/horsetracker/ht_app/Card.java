package com.lucidlogic.horsetracker.ht_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itowey on 23/05/16.
 */
public class Card {

    private String track;
    private String trackGoing;
    private String trackSurface;
    private String trackUrl;
    private List<Race> races = new ArrayList<>();

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getTrackGoing() {
        return trackGoing;
    }

    public void setTrackGoing(String trackGoing) {
        this.trackGoing = trackGoing;
    }

    public String getTrackSurface() {
        return trackSurface;
    }

    public void setTrackSurface(String trackSurface) {
        this.trackSurface = trackSurface;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }

}
