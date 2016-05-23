package com.lucidlogic.horsetracker.ht_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RececardActivity extends AppCompatActivity {

    private static final String TAG = RececardActivity.class.getName();
    private AnimatedExpandableListView listView;
    private ExampleAdapter adapter;
    private RacecardServiceClient racecardServiceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rececard);
        List<GroupItem> items = new ArrayList<GroupItem>();

        racecardServiceClient = new RacecardServiceClient(this);

        racecardServiceClient
                .getRacecardClient()
                .getRacecard()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleSubscriber<RacecardResponse>() {

                        @Override
                        public void onSuccess(RacecardResponse racecardResponse) {
                            setAdapter(racecardResponse);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }
                    });
    }

    private void setAdapter(RacecardResponse racecardResponse){
        adapter = new ExampleAdapter(this);
        adapter.setData(racecardResponse);

        listView = (AnimatedExpandableListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else {
                    listView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });

    }


}