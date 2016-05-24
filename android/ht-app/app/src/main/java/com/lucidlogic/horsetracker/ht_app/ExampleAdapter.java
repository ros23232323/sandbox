package com.lucidlogic.horsetracker.ht_app;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Adapter for our list of {@link GroupItem}s.
 */
public class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
  private static final String TAG = ExampleAdapter.class.getName();

  private final LayoutInflater inflater;

  private List<CardDTO> cards;

  public ExampleAdapter(final Context context) {
    this.inflater = LayoutInflater.from(context);
  }

  public void setData(final RacecardDTO racecardResponse) {
    this.cards = racecardResponse.getCards();
  }

  @Override
  public RaceDTO getChild(final int groupPosition, final int childPosition) {
    return this.cards.get(groupPosition).getRaces().get(childPosition);
  }

  @Override
  public long getChildId(final int groupPosition, final int childPosition) {
    return childPosition;
  }

  @Override
  public View getRealChildView(final int groupPosition, final int childPosition, final boolean isLastChild,
    View convertView, final ViewGroup parent) {
    final ChildHolder holder;
    final RaceDTO race = getChild(groupPosition, childPosition);
    if (convertView == null) {
      holder = new ChildHolder();
      convertView = this.inflater.inflate(R.layout.list_item, parent, false);
      final LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.raceSummary);
      ll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
          Log.i(TAG, "TODO :: Navigate to activity " + race.getRacecardUrl());
        }
      });
      holder.title = (TextView) convertView.findViewById(R.id.textTitle);
      holder.hint = (TextView) convertView.findViewById(R.id.textHint);
      convertView.setTag(holder);
    }
    else {
      holder = (ChildHolder) convertView.getTag();
    }

    holder.title.setText(race.getRaceTime() + "\t" + race.getRaceName());
    holder.hint.setText("");

    return convertView;
  }

  @Override
  public int getRealChildrenCount(final int groupPosition) {
    return this.cards.get(groupPosition).getRaces().size();
  }

  @Override
  public CardDTO getGroup(final int groupPosition) {
    return this.cards.get(groupPosition);
  }

  @Override
  public int getGroupCount() {
    return this.cards.size();
  }

  @Override
  public long getGroupId(final int groupPosition) {
    return groupPosition;
  }

  @Override
  public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView,
    final ViewGroup parent) {
    final GroupHolder holder;
    final CardDTO card = getGroup(groupPosition);
    if (convertView == null) {
      holder = new GroupHolder();
      convertView = this.inflater.inflate(R.layout.group_item, parent, false);
      holder.title = (TextView) convertView.findViewById(R.id.textTitle);
      convertView.setTag(holder);
    }
    else {
      holder = (GroupHolder) convertView.getTag();
    }

    holder.title.setText(card.getTrack());

    return convertView;
  }

  @Override
  public boolean hasStableIds() {
    return true;
  }

  @Override
  public boolean isChildSelectable(final int arg0, final int arg1) {
    return true;
  }

}
