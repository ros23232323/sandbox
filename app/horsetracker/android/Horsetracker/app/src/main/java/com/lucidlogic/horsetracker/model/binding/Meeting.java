package com.lucidlogic.horsetracker.model.binding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.lucidlogic.horsetracker.BR;
import com.lucidlogic.horsetracker.BeanTransformers;
import com.lucidlogic.horsetracker.R;
import com.lucidlogic.horsetracker.adapter.MeetingAdapter;
import com.lucidlogic.horsetracker.adapter.RaceAdapter;
import com.lucidlogic.horsetracker.layoutmanager.CustomLinearLayoutManager;
import com.lucidlogic.horsetracker.model.parse.MeetingParse;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ian on 08/06/16.
 */
public class Meeting extends BaseObservable implements Parcelable {

    private static final String TAG = Meeting.class.getName();

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {

        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    private String id;
    private String track;
    private String going;
    private String surface;
    private String url;
    private transient boolean showRacesList = false;
    private List<Race> races = new ArrayList<>();

    protected Meeting(Parcel in) {
        id = in.readString();
        track = in.readString();
        going = in.readString();
        surface = in.readString();
        url = in.readString();
        races = in.createTypedArrayList(Race.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(track);
        dest.writeString(going);
        dest.writeString(surface);
        dest.writeString(url);
        dest.writeTypedList(races);
    }

    @Override
    public int describeContents() {
        return 0;
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
    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
        notifyPropertyChanged(BR.races);

    }

    public Meeting() {
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

    public void showRaces(View v){

        Log.i(TAG,"Expand race list view " + this.showRacesList);
        final ExpandableLinearLayout expandableLinearLayout =
                (ExpandableLinearLayout)((LinearLayout)v.getParent().getParent()).getChildAt(2);


        if(this.races.size() == 0) {
            ParseQuery.getQuery(MeetingParse.class)
                .whereEqualTo("objectId", this.getId())
                .include("races")
                .getInBackground(this.getId(), new GetCallback<MeetingParse>() {
                    @Override
                    public void done(MeetingParse meetingParse, ParseException e) {
                        Meeting meeting = BeanTransformers.racecardFromRacecardParse(meetingParse, true);
                        final RecyclerView rv = (RecyclerView) expandableLinearLayout.findViewById(R.id.races_rv);
                        rv.setHasFixedSize(true);
                        rv.setLayoutManager(new CustomLinearLayoutManager(expandableLinearLayout.getContext(), meeting.getRaces().size()));
                        rv.setAdapter(new RaceAdapter(meeting.getRaces()));
//                        ViewGroup.LayoutParams params=rv.getLayoutParams();
//                        params.height=100*meeting.getRaces().size();
//                        rv.setLayoutParams(params);
                        setRaces(meeting.getRaces());
                    }
                });
        }

        setShowRacesList(!showRacesList);
        expandableLinearLayout.toggle();
    }

    @Bindable
    public boolean isShowRacesList() {
        return showRacesList;
    }

    public void setShowRacesList(boolean showRacesList) {
        this.showRacesList = showRacesList;
        notifyPropertyChanged(BR.showRacesList);
    }
}

