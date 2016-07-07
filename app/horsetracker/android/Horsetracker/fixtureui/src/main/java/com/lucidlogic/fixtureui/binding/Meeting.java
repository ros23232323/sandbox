package com.lucidlogic.fixtureui.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.lucidlogic.fixtureui.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian on 08/06/16.
 */
public class Meeting extends BaseObservable implements Parcelable{

    private String id;
    private String track;
    private String going;
    private String surface;
    private String url;
    private transient boolean showRacesList = false;
    private List<Race> races = new ArrayList<>();

    public Meeting() {
    }

    protected Meeting(Parcel in) {
        id = in.readString();
        track = in.readString();
        going = in.readString();
        surface = in.readString();
        url = in.readString();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
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
    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
        notifyPropertyChanged(BR.track);

    }

    @Bindable
    public String getGoing() {
        return going;
    }

    public void setGoing(String going) {
        this.going = going;
        notifyPropertyChanged(BR.going);

    }

    @Bindable
    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
        notifyPropertyChanged(BR.surface);

    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);

    }

    @Bindable
    public boolean isShowRacesList() {
        return showRacesList;
    }

    public void setShowRacesList(boolean showRacesList) {
        this.showRacesList = showRacesList;
        notifyPropertyChanged(BR.showRacesList);

    }

    @Bindable
    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
        notifyPropertyChanged(BR.races);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(track);
        dest.writeString(going);
        dest.writeString(surface);
        dest.writeString(url);
    }

    public void showRaces(View view){

    }
}

