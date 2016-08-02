package com.lucidlogic.horsetracker.handler;

import android.view.View;

import com.lucidlogic.horsetracker.model.Entity;
import com.lucidlogic.horsetracker.model.dto.EntityDTO;
import com.lucidlogic.horsetracker.model.dto.UserTrackEntityDTO;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import timber.log.Timber;

/**
 * Created by ian on 02/08/16.
 */
public class EntityClickHandler {

    public void onTrackEntity(View v, Entity e){
        EntityDTO entityDTO = ParseObject.createWithoutData(EntityDTO.class, e.getId());
        UserTrackEntityDTO userTrackEntityDTO = new UserTrackEntityDTO();
        userTrackEntityDTO.setUser(ParseUser.getCurrentUser());
        userTrackEntityDTO.setEntityDTO(entityDTO);
        userTrackEntityDTO.saveEventually(exception -> Timber.i("Save complete") );
    }

    public void onViewEntityProfile(View v, Entity e){
        EntityDTO entityDTO = ParseObject.createWithoutData(EntityDTO.class, e.getId());
        UserTrackEntityDTO userTrackEntityDTO = new UserTrackEntityDTO();
        userTrackEntityDTO.setUser(ParseUser.getCurrentUser());
        userTrackEntityDTO.setEntityDTO(entityDTO);
        userTrackEntityDTO.saveEventually(exception -> Timber.i("Save complete") );
    }
}
