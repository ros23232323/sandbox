package com.lucidlogic.horsetracker.model.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.lucidlogic.horsetracker.BR;

/**
 * Created by ian on 08/06/16.
 */
public class Race extends BaseObservable implements Parcelable {
    private String time;
    private String name;
    private String url;
    private String abandoned;
    private String resultUrl;

    public Race() {
    }

    protected Race(Parcel in) {
        time = in.readString();
        name = in.readString();
        url = in.readString();
        abandoned = in.readString();
        resultUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(abandoned);
        dest.writeString(resultUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Race> CREATOR = new Creator<Race>() {
        @Override
        public Race createFromParcel(Parcel in) {
            return new Race(in);
        }

        @Override
        public Race[] newArray(int size) {
            return new Race[size];
        }
    };

    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
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
    public String getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(String abandoned) {
        this.abandoned = abandoned;
        notifyPropertyChanged(BR.abandoned);
    }

    @Bindable
    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
        notifyPropertyChanged(BR.resultUrl);
    }
}
