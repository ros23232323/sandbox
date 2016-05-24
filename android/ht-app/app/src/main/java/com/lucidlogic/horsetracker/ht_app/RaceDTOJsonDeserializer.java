package com.lucidlogic.horsetracker.ht_app;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Created by itowey on 24/05/16.
 */
public class RaceDTOJsonDeserializer implements JsonDeserializer<RaceDTO> {

  @Override
  public RaceDTO deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException {

    final RaceDTO raceDTO = new RaceDTO();
    final JsonElement results = json.getAsJsonObject().get("results").getAsJsonArray().get(0);

    final JsonArray ja = results.getAsJsonObject().get("runners").getAsJsonArray();
    RunnerDTO runner = null;
    for (int i = 0; i < ja.size(); i++) {
      runner = new RunnerDTO();
      runner.setAge(ja.get(i).getAsJsonObject().get("age").getAsString());
      runner.setBreeding(ja.get(i).getAsJsonObject().get("breeding").getAsString());
      runner.setForm(ja.get(i).getAsJsonObject().get("form").getAsString());
      runner.setHorse(ja.get(i).getAsJsonObject().get("horse").getAsString());
      runner.setOr(ja.get(i).getAsJsonObject().get("or").getAsString());
      runner.setProfileUrl(ja.get(i).getAsJsonObject().get("profile_url").getAsString());
      runner.setRunning(ja.get(i).getAsJsonObject().get("running").getAsBoolean());
      runner.setSlikImgUrl(ja.get(i).getAsJsonObject().get("silk_img_link").getAsString());
      runner.setSp(ja.get(i).getAsJsonObject().get("sp").getAsString());
      runner.setStall(ja.get(i).getAsJsonObject().get("stall").getAsString());
      runner.setWeight(ja.get(i).getAsJsonObject().get("weight").getAsString());

      raceDTO.getRunners().add(runner);
    }
    runner = null;
    return raceDTO;
  }
}
