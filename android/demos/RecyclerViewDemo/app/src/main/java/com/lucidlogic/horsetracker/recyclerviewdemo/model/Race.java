package com.lucidlogic.horsetracker.recyclerviewdemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itowey on 25/05/16.
 */
public class Race {
    private String abandoned;

    private String raceName;

    private String raceTime;

    private String racecardUrl;

    private String resultUrl;

    private List<Runner> runners = new ArrayList<>();

    public List<Runner> getRunners() {
        return this.runners;
    }

    public void setRunners(final List<Runner> runners) {
        this.runners = runners;
    }

    public String getAbandoned() {
        return this.abandoned;
    }

    public void setAbandoned(final String abandoned) {
        this.abandoned = abandoned;
    }

    public String getRaceName() {
        return this.raceName;
    }

    public void setRaceName(final String raceName) {
        this.raceName = raceName;
    }

    public String getRaceTime() {
        return this.raceTime;
    }

    public void setRaceTime(final String raceTime) {
        this.raceTime = raceTime;
    }

    public String getRacecardUrl() {
        return this.racecardUrl;
    }

    public void setRacecardUrl(final String racecardUrl) {
        this.racecardUrl = racecardUrl;
    }

    public String getResultUrl() {
        return this.resultUrl;
    }

    public void setResultUrl(final String resultUrl) {
        this.resultUrl = resultUrl;
    }
}
