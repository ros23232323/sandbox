package com.lucidlogic.horsetracker.adapter;

import com.lucidlogic.fixtureui.binding.Race;
import com.lucidlogic.horsetracker.BR;
import com.lucidlogic.horsetracker.R;

import java.util.List;

/**
 * Created by ian on 11/06/16.
 */
public class RaceAdapter extends GenericDatabindingAdapter<Race> {

    public RaceAdapter() {
        super(BR.race, R.layout.race_card);
    }
    public RaceAdapter(List<Race> races) {
        super(races, BR.race, R.layout.race_card);
    }
}
