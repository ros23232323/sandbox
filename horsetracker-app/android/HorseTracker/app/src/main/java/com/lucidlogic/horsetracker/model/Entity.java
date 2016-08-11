package com.lucidlogic.horsetracker.model;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Map;

/**
 * Created by ian on 23/07/16.
 */
public class Entity extends BaseObservable implements Parcelable{

    private String id;
    private String type;
    private String name;
    private Date edCollectDate;
    private Map<String, Parcelable> entityDetails;
    private String profileUrl;

    public Entity() {
    }

    protected Entity(Parcel in) {
        id = in.readString();
        type = in.readString();
        name = in.readString();
        profileUrl = in.readString();
        in.readMap(entityDetails, this.getClass().getClassLoader());
        edCollectDate = new Date(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(profileUrl);
        dest.writeMap(entityDetails);
        dest.writeLong(edCollectDate.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Entity> CREATOR = new Creator<Entity>() {
        @Override
        public Entity createFromParcel(Parcel in) {
            return new Entity(in);
        }

        @Override
        public Entity[] newArray(int size) {
            return new Entity[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }


    public Date getEdCollectDate() {
        return edCollectDate;
    }

    public void setEdCollectDate(Date edCollectDate) {
        this.edCollectDate = edCollectDate;
    }

    public Map<String, Parcelable> getEntityDetails() {
        return entityDetails;
    }

    public void setEntityDetails(Map<String, Parcelable> entityDetails) {
        this.entityDetails = entityDetails;
    }
}
