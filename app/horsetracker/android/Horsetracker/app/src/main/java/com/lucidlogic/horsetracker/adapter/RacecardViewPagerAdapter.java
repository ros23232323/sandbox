package com.lucidlogic.horsetracker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.lucidlogic.horsetracker.fragment.RacecardFragment;
import com.lucidlogic.horsetracker.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian on 05/06/16.
 */
public class RacecardViewPagerAdapter extends FragmentPagerAdapter {

    private List<RacecardFragment> fragments = new ArrayList<>();

    public RacecardViewPagerAdapter(FragmentManager fm, List<RacecardFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return DateUtils.getDisplayRacecardDateFormat(fragments.get(position).getRacecard().getDate());
    }

    public List<RacecardFragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<RacecardFragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

}
