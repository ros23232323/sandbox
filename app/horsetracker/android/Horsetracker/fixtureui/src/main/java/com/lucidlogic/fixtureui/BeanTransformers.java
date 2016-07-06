package com.lucidlogic.fixtureui;


import com.lucidlogic.fixtureui.binding.Meeting;
import com.lucidlogic.fixtureui.binding.Race;
import com.lucidlogic.fixtureui.binding.Racecard;
import com.lucidlogic.fixtureui.parse.MeetingParse;
import com.lucidlogic.fixtureui.parse.RaceParse;
import com.lucidlogic.fixtureui.parse.RacecardParse;
import com.parse.ParseException;

/**
 * Created by ian on 08/06/16.
 */
public class BeanTransformers {

    public static Racecard racecardFromRacecardParse(RacecardParse racecardParse) {
        Racecard racecard = new Racecard();
        racecard.setMisc("TODO : " +  racecardParse .getDate());
        racecard.setId(racecardParse.getObjectId());
        for(int i  = 0 ; i < racecardParse.getMeetings().size(); i++){
            racecard.getMeetings().add(racecardFromRacecardParse(racecardParse.getMeetings().get(i), true));
        }
        racecard.setDate(racecardParse.getDate());


        return racecard;
    }

    public static Meeting racecardFromRacecardParse(MeetingParse meetingParse, boolean withRaces) {
        Meeting meeting = new Meeting();
        meeting.setGoing(meetingParse.getTrackGoing());
        meeting.setId(meetingParse.getObjectId());
        meeting.setSurface(meetingParse.getTrackSurface());
        meeting.setTrack(meetingParse.getTrack());
        meeting.setUrl(meetingParse.getTrackUrl());
        if (withRaces) {
            for (int i = 0; i < meetingParse.getRaces().size(); i++) {
                meeting.getRaces().add(raceFromRaceParse(meetingParse.getRaces().get(i)));
            }
        }
        return meeting;
    }

    public static Race raceFromRaceParse(RaceParse raceParse) {
        Race race = new Race();
        try {
            raceParse.fetchIfNeeded();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        race.setUrl(raceParse.getRaceUrl());
        race.setAbandoned(raceParse.getAbandoned());
        race.setName(raceParse.getRaceName());
        race.setTime(raceParse.getRaceTime());
        race.setResultUrl(raceParse.getResultUrl());
        return race;
    }
}
