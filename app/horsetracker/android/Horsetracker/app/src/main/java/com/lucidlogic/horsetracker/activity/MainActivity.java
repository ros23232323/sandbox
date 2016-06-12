package com.lucidlogic.horsetracker.activity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lucidlogic.horsetracker.BeanTransformers;
import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.adapter.RacecardViewPagerAdapter;
import com.lucidlogic.horsetracker.fragment.RacecardFragment;
import com.lucidlogic.horsetracker.model.binding.Racecard;
import com.lucidlogic.horsetracker.model.parse.RacecardParse;
import com.lucidlogic.horsetracker.utils.DateUtils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
    }

    private void initViewPager(){
        new ParseQuery<RacecardParse>("Racecard")
            .include("meetings")
            .addAscendingOrder("date")
            .findInBackground(new FindCallback<RacecardParse>() {
                @Override
                public void done(List<RacecardParse> racecardParses, ParseException e) {
                int currentPageIdx = 0;

                List<RacecardFragment> racecardFragments = new ArrayList<RacecardFragment>();
                for(int i = 0; i < racecardParses.size(); i++){
                    //Convert to bindable objects
                    Racecard racecard = BeanTransformers.racecardFromRacecardParse(racecardParses.get(i));

                    //Get index of todays racecard for setting default position
                    Date now = new Date();
                    if (DateUtils.compareDate(racecard.getDate(), now)){
                        currentPageIdx = i;
                    }
                    racecardFragments.add(RacecardFragment.newInstance(racecard));
                }


                ViewPager vpPager = (ViewPager) findViewById(R.id.racecard_vp);
                adapterViewPager = new RacecardViewPagerAdapter(getSupportFragmentManager(), racecardFragments);
                vpPager.setAdapter(adapterViewPager);
                vpPager.setOffscreenPageLimit(racecardParses.size()/2);
//                vpPager.setCurrentItem(currentPageIdx);
            }
                });
    }
}
