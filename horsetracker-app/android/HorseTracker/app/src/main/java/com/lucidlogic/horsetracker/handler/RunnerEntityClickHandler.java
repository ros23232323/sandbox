package com.lucidlogic.horsetracker.handler;

import android.view.View;

import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.model.Entity;
import com.lucidlogic.horsetracker.model.Race;
import com.lucidlogic.horsetracker.model.Runner;
import com.lucidlogic.horsetracker.nav.AppNavigationManager;
import com.lucidlogic.horsetracker.view.impl.EntitySelectedDialogFragment;

import timber.log.Timber;

/**
 * Created by ian on 12/07/16.
 */
public class RunnerEntityClickHandler {

    public void onHorseClick(View v, Entity entity){
        Timber.i("HORSE :: %s %s clicked" ,entity.getName(), entity.getId());
        EntitySelectedDialogFragment entitySelectedDialogFragment
                = EntitySelectedDialogFragment.newInstance(R.layout.fragment_dialog_selected, entity);
        entitySelectedDialogFragment.show(AppNavigationManager.getFragmentManager(),"Dialog Fragment");
    }

    public void onJockeyClick(View v, Entity entity){
        Timber.i("JOCKEY :: %s %s clicked" ,entity.getName(), entity.getId());
        EntitySelectedDialogFragment entitySelectedDialogFragment
                = EntitySelectedDialogFragment.newInstance(R.layout.fragment_dialog_selected, entity);
        entitySelectedDialogFragment.show(AppNavigationManager.getFragmentManager(),"Dialog Fragment");
    }

}
