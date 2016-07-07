package com.lucidlogic.horsetracker.activity;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lucidlogic.fixtureui.BeanTransformers;
import com.lucidlogic.fixtureui.binding.Racecard;
import com.lucidlogic.fixtureui.parse.RacecardParse;
import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.adapter.RacecardViewPagerAdapter;
import com.lucidlogic.horsetracker.fragment.RacecardFragment;
import com.parse.ParseQuery;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.parse.ParseObservable;
import rx.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fixture);
        initViewPager();
    }

    private void initViewPager(){

        ParseObservable.find(ParseQuery.getQuery(RacecardParse.class)
            .include("meetings")
            .addAscendingOrder("date"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<RacecardParse, RacecardFragment>() {
                    @Override
                    public RacecardFragment call(RacecardParse racecardParse) {
                        Racecard racecard = BeanTransformers.racecardFromRacecardParse(racecardParse);
                        return RacecardFragment.newInstance(racecard);
                    }
                })
                .toList()
                .subscribe(new Observer<List<RacecardFragment>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(List<RacecardFragment> racecardFragments) {
                        ViewPager vpPager = (ViewPager) findViewById(R.id.racecard_vp);
                        adapterViewPager = new RacecardViewPagerAdapter(getSupportFragmentManager(), racecardFragments);
                        vpPager.setAdapter(adapterViewPager);
                        vpPager.setOffscreenPageLimit(2);
                    }
                });
    }
}