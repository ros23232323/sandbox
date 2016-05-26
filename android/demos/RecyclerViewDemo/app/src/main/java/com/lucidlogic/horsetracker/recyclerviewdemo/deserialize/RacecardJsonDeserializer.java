package com.lucidlogic.horsetracker.recyclerviewdemo.deserialize;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lucidlogic.horsetracker.recyclerviewdemo.model.Card;
import com.lucidlogic.horsetracker.recyclerviewdemo.model.Race;
import com.lucidlogic.horsetracker.recyclerviewdemo.model.Racecard;

import java.lang.reflect.Type;


/**
 * Created by itowey on 25/05/16.
 */
public class RacecardJsonDeserializer  implements JsonDeserializer<Racecard> {
    @Override
    public Racecard deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        final Racecard racecardDTO = new Racecard();
        racecardDTO.setRequestID(json.getAsJsonObject().get("requestID").getAsString());
        final JsonElement results = json.getAsJsonObject().get("results").getAsJsonArray().get(0);

        final JsonArray ja = results.getAsJsonObject().get("cards").getAsJsonArray();
        Card card = null;
        Race race = null;
        for (int i = 0; i < ja.size(); i++) {
            card = new Card();
            card.setTrack(ja.get(i).getAsJsonObject().get("track").getAsString());
            card.setTrackGoing(ja.get(i).getAsJsonObject().get("track_going").getAsString());
            card.setTrackSurface(ja.get(i).getAsJsonObject().get("track_surface").getAsString());
            card.setTrackUrl(ja.get(i).getAsJsonObject().get("track_url").getAsString());
            racecardDTO.getCards().add(card);
            final JsonArray races = ja.get(i).getAsJsonObject().get("races").getAsJsonArray();
            race = new Race();
            for (int j = 0; j < races.size(); j++) {
                race.setAbandoned(races.get(j).getAsJsonObject().get("abandoned").getAsString());
                race.setRaceName(races.get(j).getAsJsonObject().get("race_name").getAsString());
                race.setRaceTime(races.get(j).getAsJsonObject().get("race_time").getAsString());
                race.setRacecardUrl(races.get(j).getAsJsonObject().has("racecard_url")
                        ? races.get(j).getAsJsonObject().get("racecard_url").getAsString()
                        : "");
                race.setResultUrl(races.get(j).getAsJsonObject().has("result_url")
                        ? races.get(j).getAsJsonObject().get("result_url").getAsString()
                        : "");
                card.getRaces().add(race);
            }
        }
        card = null;
        race = null;
        return racecardDTO;
    }
}
