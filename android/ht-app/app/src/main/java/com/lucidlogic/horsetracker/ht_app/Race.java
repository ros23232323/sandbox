package com.lucidlogic.horsetracker.ht_app;

/**
 * Created by itowey on 23/05/16.
 */
public class Race {
    private String abandoned;
    private String  raceName;
    private String  raceTime;
    private String  racecardUrl;

    private String  resultUrl;

    public String getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(String abandoned) {
        this.abandoned = abandoned;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(String raceTime) {
        this.raceTime = raceTime;
    }

    public String getRacecardUrl() {
        return racecardUrl;
    }

    public void setRacecardUrl(String racecardUrl) {
        this.racecardUrl = racecardUrl;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }
}
