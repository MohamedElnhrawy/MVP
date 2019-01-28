
package com.example.mohamedelnhrawy.Base.data.network.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class City {

    @SerializedName("country")
    private String mCountry;
    @SerializedName("geoname_id")
    private Long mGeonameId;
    @SerializedName("iso2")
    private String mIso2;
    @SerializedName("lat")
    private Double mLat;
    @SerializedName("lon")
    private Double mLon;
    @SerializedName("name")
    private String mName;
    @SerializedName("population")
    private Long mPopulation;
    @SerializedName("type")
    private String mType;

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public Long getGeonameId() {
        return mGeonameId;
    }

    public void setGeonameId(Long geonameId) {
        mGeonameId = geonameId;
    }

    public String getIso2() {
        return mIso2;
    }

    public void setIso2(String iso2) {
        mIso2 = iso2;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        mLat = lat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double lon) {
        mLon = lon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getPopulation() {
        return mPopulation;
    }

    public void setPopulation(Long population) {
        mPopulation = population;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
