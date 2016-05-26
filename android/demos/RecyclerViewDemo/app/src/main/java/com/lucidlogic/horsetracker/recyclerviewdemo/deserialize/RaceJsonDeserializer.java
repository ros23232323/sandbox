package com.lucidlogic.horsetracker.recyclerviewdemo.deserialize;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lucidlogic.horsetracker.recyclerviewdemo.model.Race;
import com.lucidlogic.horsetracker.recyclerviewdemo.model.Runner;

import java.lang.reflect.Type;

/**
 * Created by itowey on 24/05/16.
 */
public class RaceJsonDeserializer implements JsonDeserializer<Race> {

  @Override
  public Race deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
    throws JsonParseException {

    final Race race = new Race();
    final JsonElement results = json.getAsJsonObject().get("results").getAsJsonArray().get(0);

    final JsonArray ja = results.getAsJsonObject().get("runners").getAsJsonArray();
    Runner runner = null;
    for (int i = 0; i < ja.size(); i++) {
      runner = new Runner();
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

      race.getRunners().add(runner);
    }
    runner = null;
    return race;
  }
}
