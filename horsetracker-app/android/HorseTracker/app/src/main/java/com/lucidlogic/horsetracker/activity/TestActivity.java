package com.lucidlogic.horsetracker.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.lucidlogic.horsetracker.view.impl.RaceFragment;
import com.lucidlogic.horsetracker.view.impl.RacecardFragment;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            this.getSupportFragmentManager().beginTransaction().add(RaceFragment.newInstance("SOX5iuKPlk"),"").commit();
    }

}
