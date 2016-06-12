package com.lucidlogic.horsetracker.model.parse;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by ian on 05/06/16.
 */
@ParseClassName("Race")
public class RaceParse extends ParseObject {

    public RaceParse() {
    }

    public String getRaceTime(){return getString("race_time");}
    public String getRaceName(){
        return getString("race_name");
    }
    public String getAbandoned(){
        return getString("abandoned");
    }
    public String getRaceUrl(){
        return getString("race_url");
    }
    public String getResultUrl(){
        return getString("result_url");
    }

    public void setRaceTime(String raceTime){
        put("race_time", raceTime);
    }
    public void getRaceName(String raceName){
        put("race_name", raceName);
    }
    public void setAbandoned(String abandoned){
        put("abandoned", abandoned);
    }
    public void setRaceUrl(String raceUrl){
        put("race_url", raceUrl);
    }
    public void getResultUrl(String resultUrl){
        put("result_url", resultUrl);
    }

}
