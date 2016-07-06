package com.lucidlogic.fixtureui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.lucidlogic.fixtureui.binding.Meeting;
import com.lucidlogic.fixtureui.binding.Race;
import com.lucidlogic.fixtureui.binding.Racecard;

/**
 * Created by ian on 21/06/16.
 */
public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private Racecard racecard;

    public CustomExpandableListAdapter (Context context, Racecard racecard) {
        this._context = context;
        this.racecard = racecard;
    }

    @Override
    public Race getChild(int groupPosition, int childPosititon) {
        Race r = this.racecard.getMeetings().get(groupPosition).getRaces().get(childPosititon);
        return r;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Race meeting = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById (R.id.lblListItem);

        txtListChild.setText(meeting.getName());
        txtListChild = (TextView) convertView
                .findViewById (R.id.lblListItemTime);

        txtListChild.setText(meeting.getTime());
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
        Meeting meeting = (Meeting) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById (R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(meeting .getTrack());

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

