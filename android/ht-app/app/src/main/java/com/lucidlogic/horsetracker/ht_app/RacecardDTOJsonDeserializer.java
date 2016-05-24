package com.lucidlogic.horsetracker.ht_app;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Created by itowey on 23/05/16.
 */
public class RacecardDTOJsonDeserializer implements JsonDeserializer<RacecardDTO> {
  @Override
  public RacecardDTO deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException {

    final RacecardDTO racecardDTO = new RacecardDTO();
    racecardDTO.setRequestID(json.getAsJsonObject().get("requestID").getAsString());
    final JsonElement results = json.getAsJsonObject().get("results").getAsJsonArray().get(0);

    final JsonArray ja = results.getAsJsonObject().get("cards").getAsJsonArray();
    CardDTO card = null;
    RaceDTO race = null;
    for (int i = 0; i < ja.size(); i++) {
      card = new CardDTO();
      card.setTrack(ja.get(i).getAsJsonObject().get("track").getAsString());
      card.setTrackGoing(ja.get(i).getAsJsonObject().get("track_going").getAsString());
      card.setTrackSurface(ja.get(i).getAsJsonObject().get("track_surface").getAsString());
      card.setTrackUrl(ja.get(i).getAsJsonObject().get("track_url").getAsString());
      racecardDTO.getCards().add(card);
      final JsonArray races = ja.get(i).getAsJsonObject().get("races").getAsJsonArray();
      race = new RaceDTO();
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
