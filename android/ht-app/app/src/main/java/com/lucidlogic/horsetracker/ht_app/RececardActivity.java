package com.lucidlogic.horsetracker.ht_app;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RececardActivity extends AppCompatActivity {

  private static final String TAG = RececardActivity.class.getName();

  private AnimatedExpandableListView listView;

  private ExampleAdapter adapter;

  private RacecardServiceClient racecardServiceClient;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rececard);
    final List<GroupItem> items = new ArrayList<>();

    this.racecardServiceClient = new RacecardServiceClient(this);

    this.racecardServiceClient.getRacecardClient().getRacecard(getString(R.string.racecard_query))
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleSubscriber<RacecardDTO>() {

          @Override
          public void onSuccess(final RacecardDTO racecardDTO) {
            setAdapter(racecardDTO);
          }

          @Override
          public void onError(final Throwable e) {
            e.printStackTrace();
          }
        });
  }

  private void setAdapter(final RacecardDTO racecardDTO) {
    this.adapter = new ExampleAdapter(this);
    this.adapter.setData(racecardDTO);

    this.listView = (AnimatedExpandableListView) findViewById(R.id.listView);
    this.listView.setAdapter(this.adapter);

    // In order to show animations, we need to use a custom click handler
    // for our ExpandableListView.
    this.listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

      @Override
      public boolean onGroupClick(final ExpandableListView parent, final View v, final int groupPosition,
        final long id) {
        // We call collapseGroupWithAnimation(int) and
        // expandGroupWithAnimation(int) to animate group
        // expansion/collapse.
        if (com.lucidlogic.horsetracker.ht_app.RececardActivity.this.listView.isGroupExpanded(groupPosition)) {
          com.lucidlogic.horsetracker.ht_app.RececardActivity.this.listView.collapseGroupWithAnimation(groupPosition);
        }
        else {
          com.lucidlogic.horsetracker.ht_app.RececardActivity.this.listView.expandGroupWithAnimation(groupPosition);
        }
        return true;
      }

    });

  }

}
