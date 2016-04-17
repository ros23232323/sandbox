package com.lucidlogic.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lucidlogic.common.Meeting;
import com.lucidlogic.common.Race;
import com.lucidlogic.common.Racecard;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian on 16/04/16.
 */
public class RacecardDeserializer implements JsonDeserializer<Racecard> {

    @Override
    public Racecard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        JsonArray meetingsJsonArray = jo.get("cards").getAsJsonArray();
        List<Meeting> meetings  = new ArrayList<>();
        for(int i = 0; i < meetingsJsonArray.size(); i++){
            JsonObject meeting = meetingsJsonArray.get(i).getAsJsonObject();
            String track = meeting.get("track").getAsString();
            String trackUrl = meeting.get("track_url").getAsString();
            String trackGoing = meeting.get("track_going").getAsString();
            String trackSurface = meeting.get("track_surface").getAsString();
            List<Race> races = new ArrayList<>();
            JsonArray raceJsonArray = meeting.get("races").getAsJsonArray();
            for(int j = 0; j < raceJsonArray.size(); j++){
                JsonObject jsonObject = raceJsonArray.get(j).getAsJsonObject();
                String raceTime = jsonObject.get("race_time").getAsString();
                String raceName = jsonObject.get("race_name").getAsString();
                boolean abandoned = jsonObject.get("abandoned").getAsBoolean();
                String racecardUrl = null;
                String resultUrl = null;
                if(jsonObject.get("racecard_url") != null)
                    racecardUrl = jsonObject.get("racecard_url").getAsString();
                if(jsonObject.get("result_url") != null)
                    resultUrl = jsonObject.get("result_url").getAsString();
                races.add(new Race(raceTime, raceName, racecardUrl, resultUrl, abandoned));
            }
            meetings.add(new Meeting(track, trackUrl, trackGoing, trackSurface, races));
        }
        return new Racecard(jo.get("page_url").getAsString(), jo.get("date").getAsString(), meetings);
    }
}
