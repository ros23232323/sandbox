package com.lucidlogic.horsetracker.utils;

import android.databinding.BindingAdapter;
import android.text.format.*;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by ian on 08/06/16.
 */
public class CustomBindingAdapters {

    @BindingAdapter(value = {"displayFormattedDate"})
    public static void setText(TextView tv, Date dt){
        if(dt != null){
            tv.setText(DateUtils.getDisplayRacecardDateFormat(dt));
        }
    }
}
