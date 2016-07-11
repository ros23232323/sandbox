package com.lucidlogic.horsetracker.utils;


import com.lucidlogic.horsetracker.model.Meeting;
import com.lucidlogic.horsetracker.model.MeetingDTO;
import com.lucidlogic.horsetracker.model.Race;
import com.lucidlogic.horsetracker.model.RaceDTO;
import com.lucidlogic.horsetracker.model.Racecard;
import com.lucidlogic.horsetracker.model.RacecardDTO;
import com.parse.ParseException;

/**
 * Created by ian on 08/06/16.
 */
public class BeanTransformers {

    public static Racecard racecardFromRacecardDTO(RacecardDTO racecardDTO) {
        Racecard racecard = new Racecard();
        racecard.setId(racecardDTO.getObjectId());
        for(int i  = 0 ; i < racecardDTO.getMeetings().size(); i++){
            racecard.getMeetings().add(racecardFromRacecardDTO(racecardDTO.getMeetings().get(i)));
        }
        racecard.setDate(racecardDTO.getDate());


        return racecard;
    }

    public static Meeting racecardFromRacecardDTO(MeetingDTO meetingDTO) {
        Meeting meeting = new Meeting();
        meeting.setGoing(meetingDTO.getTrackGoing());
        meeting.setId(meetingDTO.getObjectId());
        meeting.setSurface(meetingDTO.getTrackSurface());
        meeting.setTrack(meetingDTO.getTrack());
        for (int i = 0; i < meetingDTO.getRaces().size(); i++) {
            meeting.getRaces().add(raceFromRaceDTO(meetingDTO.getRaces().get(i)));
        }
        return meeting;
    }

    public static Race raceFromRaceDTO(RaceDTO raceDTO) {
        Race race = new Race();
        race.setAbandoned(raceDTO.getAbandoned());
        race.setName(raceDTO.getName());
        race.setTime(raceDTO.getTime());
        return race;
    }
}
