package com.lucidlogic.fixtureui.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.lucidlogic.fixtureui.BR;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ian on 05/06/16.
 */
public class Racecard extends BaseObservable implements Parcelable{

    private String id;
    private String misc;
    private Date date;
    private List<Meeting> meetings = new ArrayList<>();

    public Racecard() {
    }

    protected Racecard(Parcel in) {
        id = in.readString();
        misc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(misc);
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
        notifyPropertyChanged(BR.misc);

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
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
        notifyPropertyChanged(BR.meetings);

    }
}
