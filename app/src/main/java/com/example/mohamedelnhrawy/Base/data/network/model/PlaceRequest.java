package com.example.mohamedelnhrawy.Base.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

public class PlaceRequest implements Parcelable {
   private double lat,lon;
   private String appid;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public static Creator<PlaceRequest> getCREATOR() {
        return CREATOR;
    }

    public PlaceRequest(double lat, double lon, String appid) {
        this.lat = lat;
        this.lon = lon;
        this.appid = appid;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lon);
        dest.writeString(this.appid);
    }

    public PlaceRequest() {
    }

    protected PlaceRequest(Parcel in) {
        this.lat = in.readDouble();
        this.lon = in.readDouble();
        this.appid = in.readString();
    }

    public static final Parcelable.Creator<PlaceRequest> CREATOR = new Parcelable.Creator<PlaceRequest>() {
        @Override
        public PlaceRequest createFromParcel(Parcel source) {
            return new PlaceRequest(source);
        }

        @Override
        public PlaceRequest[] newArray(int size) {
            return new PlaceRequest[size];
        }
    };
}
