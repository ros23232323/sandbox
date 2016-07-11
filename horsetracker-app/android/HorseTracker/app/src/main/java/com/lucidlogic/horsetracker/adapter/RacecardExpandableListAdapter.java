package com.lucidlogic.horsetracker.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.BR;
import com.lucidlogic.horsetracker.handler.RaceClickHandler;
import com.lucidlogic.horsetracker.model.Meeting;
import com.lucidlogic.horsetracker.model.Race;
import com.lucidlogic.horsetracker.model.Racecard;

public class RacecardExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private Racecard racecard;

    public RacecardExpandableListAdapter(Context context, Racecard racecard) {
        this.context = context;
        this.racecard = racecard;
    }

    @Override
    public Race getChild(int groupPosition, int childPosititon) {
        return this.racecard.getMeetings().get(groupPosition).getRaces().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Race race =getChild(groupPosition, childPosition);

        if (convertView == null) {
            ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_racecard_list_group_item, parent, false);
            viewDataBinding.setVariable(BR.race,  race);
            viewDataBinding.setVariable(BR.handler,  new RaceClickHandler());

//            LayoutInflater infalInflater = (LayoutInflater) this.context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.fragment_racecard_list_group_item, null);
            convertView  = viewDataBinding.getRoot();
        }

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Timber.i("item clicked");
//            }
//        });

//        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//        txtListChild.setText(race.getName());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.racecard.getMeetings().get(groupPosition).getRaces().size();
    }

    @Override
    public Meeting getGroup(int groupPosition) {
        return this.racecard.getMeetings().get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.racecard.getMeetings().size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Meeting meeting = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_racecard_list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(meeting.getTrack());

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}