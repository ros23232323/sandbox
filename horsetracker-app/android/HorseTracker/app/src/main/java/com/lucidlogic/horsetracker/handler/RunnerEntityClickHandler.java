package com.lucidlogic.horsetracker.handler;

import android.view.View;

import com.lucidlogic.horsetracker.model.Entity;
import com.lucidlogic.horsetracker.model.Race;
import com.lucidlogic.horsetracker.model.Runner;

import timber.log.Timber;

/**
 * Created by ian on 12/07/16.
 */
public class RunnerEntityClickHandler {

    public void onHorseClick(View v, Entity entity){
        Timber.i("HORSE :: %s %s clicked" ,entity.getName(), entity.getId());
    }

    public void onJockeyClick(View v, Entity entity){
        Timber.i("JOCKEY :: %s %s clicked" ,entity.getName(), entity.getId());
    }

}
