package com.lucidlogic.horsetracker.recyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lucidlogic.horsetracker.recyclerviewdemo.R;
import com.lucidlogic.horsetracker.recyclerviewdemo.ui.BottomBarWidget;
import com.roughike.bottombar.BottomBar;

public class TestActivity extends AppCompatActivity {

  private BottomBar mBottomBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mBottomBar = BottomBarWidget.getInstance(this, savedInstanceState);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);

    // Necessary to restore the BottomBar's state, otherwise we would
    // lose the current tab on orientation change.
    mBottomBar.onSaveInstanceState(outState);
  }
}
