package com.lucidlogic.horsetracker.ht_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter for our list of {@link GroupItem}s.
 */
public class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private static final String TAG = ExampleAdapter.class.getName();
    private LayoutInflater inflater;

    private List<Card> cards;

    public ExampleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setData(RacecardResponse racecardResponse) {
        this.cards = racecardResponse.getCards();
    }

    @Override
    public Race getChild(int groupPosition, int childPosition) {
        return cards.get(groupPosition).getRaces().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        final Race race = getChild(groupPosition, childPosition);
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.raceSummary);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG,"TODO :: Navigate to activity " + race.getRacecardUrl());
                }
            });
            holder.title = (TextView) convertView.findViewById(R.id.textTitle);
            holder.hint = (TextView) convertView.findViewById(R.id.textHint);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        holder.title.setText(race.getRaceTime() + "\t"+ race.getRaceName());
        holder.hint.setText("");

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return cards.get(groupPosition).getRaces().size();
    }

    @Override
    public Card getGroup(int groupPosition) {
        return cards.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return cards.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        Card card = getGroup(groupPosition);
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = inflater.inflate(R.layout.group_item, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.textTitle);
            convertView.setTag(holder);
        } else {
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
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

}