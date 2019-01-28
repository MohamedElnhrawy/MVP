package com.example.mohamedelnhrawy.Base.ui.addcity;

import android.location.Address;
import android.location.Location;

import com.example.mohamedelnhrawy.Base.ui.base.MvpView;

/**
 * Created by mohamedelnhrawy on 1/22/19.
 */

public interface AddCityMvpView extends MvpView {
    void onLocationUpdate(Location location);
    void onAddressUpdate(Address address);
    void onLocationSettingsUnsuccessful();
    void drawCurrentLocation(Address address);
    void drawClickedLocation(Location location);
    void clearMap();
    void showAddedSnackBar(int message);
}
