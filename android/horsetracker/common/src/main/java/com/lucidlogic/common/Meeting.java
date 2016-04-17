package com.lucidlogic.common;

import java.util.List;

/**
 * Created by ian on 16/04/16.
 */
public class Meeting {

    private String track;
    private String track_url;
    private String track_going;
    private String track_surface;
    private List<Race> race;

    public Meeting() {
    }

    public Meeting(String track, String track_url, String track_going, String track_surface, List<Race> race) {
        this.track = track;
        this.track_url = track_url;
        this.track_going = track_going;
        this.track_surface = track_surface;
        this.race = race;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getTrack_url() {
        return track_url;
    }

    public void setTrack_url(String track_url) {
        this.track_url = track_url;
    }

    public String getTrack_going() {
        return track_going;
    }

    public void setTrack_going(String track_going) {
        this.track_going = track_going;
    }

    public String getTrack_surface() {
        return track_surface;
    }

    public void setTrack_surface(String track_surface) {
        this.track_surface = track_surface;
    }

    public List<Race> getRace() {
        return race;
    }

    public void setRace(List<Race> race) {
        this.race = race;
    }
}
