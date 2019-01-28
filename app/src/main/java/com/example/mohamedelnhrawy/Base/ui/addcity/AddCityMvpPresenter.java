package com.example.mohamedelnhrawy.Base.ui.addcity;

import com.example.mohamedelnhrawy.Base.data.db.model.Location;
import com.example.mohamedelnhrawy.Base.ui.base.MvpPresenter;
import com.patloew.rxlocation.RxLocation;

/**
 * Created by mohamedelnhrawy on 1/22/19.
 */

public interface AddCityMvpPresenter <V extends AddCityMvpView> extends MvpPresenter<V>{

    void stopUpdateLocation();
     void requestPermissions(String[] permissions, int requestCode);
     void onPermissionsResult(boolean is_Granted);
     boolean hasPermission(String perm);
     void addLocationToDB(Location location);
}
