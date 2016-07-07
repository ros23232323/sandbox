package com.lucidlogic.fixtureui.parse;

import android.os.Parcel;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by ian on 05/06/16.
 */
@ParseClassName("Meeting")
public class MeetingParse extends ParseObject {

    public MeetingParse() {
    }

    protected MeetingParse(Parcel in) {
    }


    public String getTrackGoing(){
        return getString("track_going");
    }

    public String getTrackSurface(){
        return getString("track_surface");
    }

    public String getTrack(){
        return getString("track");
    }

    public String getTrackUrl(){
        return getString("track_url");
    }

    public List<RaceParse> getRaces(){
        return getList("races");
    }

    public void setTrackGoing(String  trackGoing){
        put("track_going", trackGoing);
    }

    public void setTrackSurface(String trackSurface){
        put("track_surface", trackSurface);
    }

    public void setTrack(String track){
        put("track", track);
    }

    public void setRaces(List<RaceParse> races){
        put("races", races);
    }

    public void setTrackUrl(String trackUrl){
        put("track_url", trackUrl);
    }

}
