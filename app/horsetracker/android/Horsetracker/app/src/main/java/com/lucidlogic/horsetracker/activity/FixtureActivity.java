package com.lucidlogic.horsetracker.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.lucidlogic.fixtureui.BeanTransformers;
import com.lucidlogic.fixtureui.CustomExpandableListAdapter;
import com.lucidlogic.fixtureui.binding.Racecard;
import com.lucidlogic.fixtureui.parse.RacecardParse;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.parse.ParseObservable;
import rx.schedulers.Schedulers;

public class FixtureActivity extends Activity {

    CustomExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected  void  onCreate (Bundle savedInstanceState) {
        super .onCreate (savedInstanceState);
        setContentView(com.lucidlogic.fixtureui.R.layout.fragment_fixture);

        ParseObservable.<RacecardParse>find(ParseQuery.getQuery(RacecardParse.class)
                .include("meetings")
                .whereEqualTo("objectId","V0qFHbs4Xz")
                .addAscendingOrder("date"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<RacecardParse, Racecard>() {
                    @Override
                    public Racecard call(RacecardParse racecardParse) {
                        return BeanTransformers.racecardFromRacecardParse(racecardParse);
                    }
                })
                .subscribe(new Observer<Racecard>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(Racecard racecard) {

                        expListView = (ExpandableListView) findViewById(com.lucidlogic.fixtureui.R.id.lvExp);
                        listAdapter = new CustomExpandableListAdapter(getApplicationContext(), racecard);
                        // setting list adapter
                        expListView.setAdapter (listAdapter);
                    }
                });


    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}