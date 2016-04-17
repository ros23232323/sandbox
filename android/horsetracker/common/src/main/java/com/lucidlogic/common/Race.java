package com.lucidlogic.common;

/**
 * Created by ian on 16/04/16.
 */
public class Race {

    private String raceTime;
    private String raceName;
    private String racecardUrl;
    private String resultUrl;
    private boolean abandoned;

    public String getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(String raceTime) {
        this.raceTime = raceTime;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
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

    public boolean isAbandoned() {
        return abandoned;
    }

    public void setAbandoned(boolean abandoned) {
        this.abandoned = abandoned;
    }

    public Race(String raceTime, String raceName, String racecardUrl, String resultUrl, boolean abandoned) {
        this.raceTime = raceTime;
        this.raceName = raceName;
        this.racecardUrl = racecardUrl;
        this.resultUrl = resultUrl;
        this.abandoned = abandoned;
    }

    public Race() {
    }
}
