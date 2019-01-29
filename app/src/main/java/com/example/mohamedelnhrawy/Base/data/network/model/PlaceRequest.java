package com.example.mohamedelnhrawy.Base.data.network.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

public class PlaceRequest implements Parcelable {
    @SerializedName("lat")
   private String lat;
    @SerializedName("lon")
    private String  lon;
    @SerializedName("appid")
    private String appid;
    @SerializedName("cnt")
    private String cnt;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCnt() {
        return cnt;
    }

    public PlaceRequest(String lat, String lon, String appid, String cnt) {
        this.lat = lat;
        this.lon = lon;
        this.appid = appid;
        this.cnt = cnt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lat);
        dest.writeString(this.lon);
        dest.writeString(this.appid);
        dest.writeString(this.cnt);
    }

    protected PlaceRequest(Parcel in) {
        this.lat = in.readString();
        this.lon = in.readString();
        this.appid = in.readString();
        this.cnt = in.readString();
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
