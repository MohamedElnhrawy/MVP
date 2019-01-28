package com.example.mohamedelnhrawy.Base.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.mohamedelnhrawy.Base.constants.DBConstant;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohamedelnhrawy on 1/23/19.
 */

@Entity(tableName = DBConstant.LOCATIONS_TABLE_NAME)
public class Location implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DBConstant.Location_ID)
    private int id;


    @ColumnInfo(name = DBConstant.Location_LATITUDE)
    private double location_latitude;

    @ColumnInfo(name = DBConstant.Location_LONIGTUDE)
    private double location_longitude;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getLocation_latitude() {
        return location_latitude;
    }

    public void setLocation_latitude(double location_latitude) {
        this.location_latitude = location_latitude;
    }

    public double getLocation_longitude() {
        return location_longitude;
    }

    public void setLocation_longitude(double location_longitude) {
        this.location_longitude = location_longitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeDouble(this.location_latitude);
        dest.writeDouble(this.location_longitude);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.id = in.readInt();
        this.location_latitude = in.readDouble();
        this.location_longitude = in.readDouble();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
