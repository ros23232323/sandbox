package com.lucidlogic.horsetracker.model.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.lucidlogic.horsetracker.BR;
import com.lucidlogic.horsetracker.model.parse.MeetingParse;
import com.lucidlogic.horsetracker.model.parse.RacecardParse;
import com.lucidlogic.horsetracker.utils.DateUtils;
import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ian on 05/06/16.
 */
public class Racecard extends BaseObservable implements Parcelable{

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String misc;
    private Date date;
    private List<Meeting> meetings = new ArrayList<>();

    public Racecard() {
    }

    protected Racecard(Parcel in) {
        id = in.readString();
        misc = in.readString();
        meetings = in.createTypedArrayList(Meeting.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(misc);
        dest.writeTypedList(meetings);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Racecard> CREATOR = new Creator<Racecard>() {
        @Override
        public Racecard createFromParcel(Parcel in) {
            return new Racecard(in);
        }

        @Override
        public Racecard[] newArray(int size) {
            return new Racecard[size];
        }
    };

    @Bindable
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
        notifyPropertyChanged(BR.meetings);
    }

    @Bindable
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
        notifyPropertyChanged(BR.misc);
    }

}
