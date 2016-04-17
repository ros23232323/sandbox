package com.lucidlogic.common;

import java.util.List;

/**
 * Created by ian on 16/04/16.
 */
public class Racecard {

    private String pageUrl;
    private String date;
    private List<Meeting> meetings;

    public Racecard(String pageUrl, String date, List<Meeting> meetings) {
        this.pageUrl = pageUrl;
        this.date = date;
        this.meetings = meetings;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }


    public String getPageUrl() {
        return pageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }
}
