package com.lucidlogic.horsetracker.handler;

import android.view.View;

import com.lucidlogic.horsetracker.model.Entity;

import timber.log.Timber;

/**
 * Created by ian on 02/08/16.
 */
public class EntityClickHandler {

    public void onEntityClick(View v, Entity e){
        Timber.i("%s %s clicked" ,e.getName(), e.getId());

    }
}
