package com.lucidlogic.horsetracker;

import com.lucidlogic.horsetracker.model.binding.Meeting;
import com.lucidlogic.horsetracker.model.binding.Race;
import com.lucidlogic.horsetracker.model.binding.Racecard;
import com.lucidlogic.horsetracker.model.parse.MeetingParse;
import com.lucidlogic.horsetracker.model.parse.RaceParse;
import com.lucidlogic.horsetracker.model.parse.RacecardParse;
import com.lucidlogic.horsetracker.utils.DateUtils;
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
            racecard.getMeetings().add(racecardFromRacecardParse(racecardParse.getMeetings().get(i), false));
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
        race.setUrl(raceParse.getRaceUrl());
        race.setAbandoned(raceParse.getAbandoned());
        race.setName(raceParse.getRaceName());
        race.setTime(raceParse.getRaceTime());
        race.setResultUrl(raceParse.getResultUrl());
        return race;
    }
}
