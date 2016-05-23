package com.lucidlogic.horsetracker.ht_app;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by itowey on 23/05/16.
 */
public class RacecardResponseJsonDeserializer implements JsonDeserializer<RacecardResponse> {
    @Override
    public RacecardResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        RacecardResponse racecardResponse = new RacecardResponse();
        racecardResponse.setRequestID(json.getAsJsonObject().get("requestID").getAsString());
        JsonElement results = json.getAsJsonObject().get("results").getAsJsonArray().get(0);

        JsonArray ja = results.getAsJsonObject().get("cards").getAsJsonArray();
        Card card = null;
        Race race = null;
        for(int i = 0; i < ja.size(); i++){
            card = new Card();
            card.setTrack(ja.get(i).getAsJsonObject().get("track").getAsString());
            card.setTrackGoing(ja.get(i).getAsJsonObject().get("track_going").getAsString());
            card.setTrackSurface(ja.get(i).getAsJsonObject().get("track_surface").getAsString());
            card.setTrackUrl(ja.get(i).getAsJsonObject().get("track_url").getAsString());
            racecardResponse.getCards().add(card);
            JsonArray races = ja.get(i).getAsJsonObject().get("races").getAsJsonArray();
            race = new Race();
            for(int j = 0; j < races.size(); j++){
                race.setAbandoned(races.get(j).getAsJsonObject().get("abandoned").getAsString());
                race.setRaceName(races.get(j).getAsJsonObject().get("race_name").getAsString());
                race.setRaceTime(races.get(j).getAsJsonObject().get("race_time").getAsString());
                race.setRacecardUrl(races.get(j).getAsJsonObject().get("racecard_url").getAsString());
                //race.setResultUrl(races.get(j).getAsJsonObject().get("result_url") == null ? "" : races.get(j).getAsJsonObject().get("result_url").getAsString());
                card.getRaces().add(race);
            }
        }
        card = null;
        race = null;
        return racecardResponse;
    }
}
