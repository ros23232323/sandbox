package com.lucidlogic.horsetracker.utils;


import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.lucidlogic.horsetracker.model.Entity;
import com.lucidlogic.horsetracker.model.Meeting;
import com.lucidlogic.horsetracker.model.Runner;
import com.lucidlogic.horsetracker.model.dto.EntityDTO;
import com.lucidlogic.horsetracker.model.dto.MeetingDTO;
import com.lucidlogic.horsetracker.model.Race;
import com.lucidlogic.horsetracker.model.dto.RaceDTO;
import com.lucidlogic.horsetracker.model.Racecard;
import com.lucidlogic.horsetracker.model.dto.RacecardDTO;
import com.lucidlogic.horsetracker.model.dto.RunnerDTO;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by ian on 08/06/16.
 */
public class BeanTransformers {

    public static Racecard racecardFromRacecardDTO(RacecardDTO racecardDTO) {
        Racecard racecard = new Racecard();
        racecard.setId(racecardDTO.getObjectId());
        racecard.getMeetings().addAll(
                Stream.of(racecardDTO.getMeetings())
                        .map(meetingDTO -> meetingFromMeetingDTO(meetingDTO))
                        .collect(Collectors.toList())
        );
        racecard.setDate(racecardDTO.getDate());


        return racecard;
    }

    public static Meeting meetingFromMeetingDTO(MeetingDTO meetingDTO) {
        Meeting meeting = new Meeting();

        meeting.setId(meetingDTO.getObjectId());
        meeting.setGoing(meetingDTO.getTrackGoing());
        meeting.setSurface(meetingDTO.getTrackSurface());
        meeting.setTrack(meetingDTO.getTrack());

        meeting.getRaces().addAll(
                Stream.of(meetingDTO.getRaces())
                .map(raceDTO -> raceFromRaceDTO(raceDTO))
                .collect(Collectors.toList()));
        return meeting;
    }

    public static Race raceFromRaceDTO(RaceDTO raceDTO) {
        Race race = new Race();
        race.setId(raceDTO.getObjectId().trim());
        race.setAbandoned(raceDTO.getAbandoned().trim());
        race.setName(raceDTO.getName().trim());
        race.setTime(raceDTO.getTime().trim());
        race.getRunners().addAll(
            Stream.of(raceDTO.getRunners())
                    .map(runnerDTO -> runnerFromRunnerDTO(runnerDTO))
                    .collect(Collectors.toList())
        );
        return race;
    }

    public static Runner runnerFromRunnerDTO(RunnerDTO runnerDTO) {
        Runner runner = new Runner ();
        runner.setId(runnerDTO.getObjectId().trim());
        runner.setAge(runnerDTO.getAge().trim());
        runner.setBreeding(runnerDTO.getBreeding().trim());
        runner.setCreatedAt(runnerDTO.getCreatedAt());
        runner.setForm(runnerDTO.getForm());
        runner.setOr(runnerDTO.getOr());
        runner.setRunning(runnerDTO.isRunning());
        runner.setSilkImgLink(runnerDTO.getSilkImgLink());
        runner.setStall(runnerDTO.getStall());
        runner.setHorse(entityFromEntityDTO(runnerDTO.getHorse()));
        runner.setTrainer(entityFromEntityDTO(runnerDTO.getTrainer()));
        runner.setJockey(entityFromEntityDTO(runnerDTO.getJockey()));
        return runner;
    }

    public static Entity entityFromEntityDTO(EntityDTO entityDTO) {
        Entity entity = new Entity();
        entity.setName(entityDTO.getName());
        entity.setId(entityDTO.getObjectId());
        entity.setProfileUrl(entityDTO.getProfileUrl());
        entity.setType(entityDTO.getType());
        return entity;
    }
}
