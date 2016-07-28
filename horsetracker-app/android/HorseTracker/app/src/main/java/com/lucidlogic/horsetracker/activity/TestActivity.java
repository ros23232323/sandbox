package com.lucidlogic.horsetracker.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lucidlogic.horsetracker.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
//        this.getSupportFragmentManager().beginTransaction().add(RaceFragment.newInstance("SOX5iuKPlk"),"").commit();
    }

}
