package com.lucidlogic.horsetracker.recyclerviewdemo.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.lucidlogic.horsetracker.recyclerviewdemo.R;
import com.lucidlogic.horsetracker.recyclerviewdemo.fragment.CardFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

/**
 * Created by itowey on 25/05/16.
 */
public class BottomBarWidget {

    private static BottomBar mBottomBar;

    private static void init(AppCompatActivity activity, Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(activity, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemRacecards) {
                    activity.getFragmentManager().beginTransaction().replace(R.id.page_content, CardFragment.newInstance(1)).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemRacecards) {
                    // The user reselected item number one, scroll your content to top.
                }
            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(activity, R.color.gray));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(activity, R.color.gray));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(activity, R.color.gray));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(activity, R.color.gray));
        mBottomBar.mapColorForTab(4, ContextCompat.getColor(activity, R.color.gray));
    }

    public static BottomBar getInstance(AppCompatActivity activity, Bundle savedInstanceState){

        if (mBottomBar == null) {
            BottomBarWidget.init(activity, savedInstanceState);
        }
        return mBottomBar;
    }
}
