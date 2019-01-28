package com.example.mohamedelnhrawy.Base.ui.addcity;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Location;
import android.util.Log;

import com.example.mohamedelnhrawy.Base.R;
import com.example.mohamedelnhrawy.Base.data.DataManager;
import com.example.mohamedelnhrawy.Base.ui.base.BasePresenter;
import com.example.mohamedelnhrawy.Base.util.rx.SchedulerProvider;
import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mohamedelnhrawy on 1/22/19.
 */

public class AddCityPresenter <V extends AddCityMvpView> extends BasePresenter<V> implements AddCityMvpPresenter<V> {

    private  LocationRequest locationRequest;

    @Inject
    public AddCityPresenter(DataManager dataManager, RxLocation rxLocation, SchedulerProvider mSchedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, rxLocation, mSchedulerProvider, compositeDisposable);
        this.locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000);
    }


    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        startLocationRefresh();
    }

    public void startLocationRefresh() {
        getCompositeDisposable().add(
                getRxLocation().settings().checkAndHandleResolution(locationRequest)
                        .flatMapObservable(this::getAddressObservable)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(getMvpView()::onAddressUpdate, throwable -> Log.e("MainPresenter", "Error fetching location/address updates", throwable))
        );
    }

    @SuppressLint("MissingPermission")
    private Observable<Address> getAddressObservable(boolean success) {
        if(success) {
            return getRxLocation().location().updates(locationRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(getMvpView()::onLocationUpdate)
                    .flatMap(this::getAddressFromLocation);


        } else {
            getMvpView().onLocationSettingsUnsuccessful();

            return getRxLocation().location().lastLocation()
                    .doOnSuccess(getMvpView()::onLocationUpdate)
                    .flatMapObservable(this::getAddressFromLocation);
        }

    }

    private Observable<Address> getAddressFromLocation(Location location) {
        return getRxLocation().geocoding().fromLocation(location).toObservable()
                .subscribeOn(Schedulers.io());
    }


    @Override
    public void stopUpdateLocation() {
        getCompositeDisposable().dispose();
    }

    @Override
    public void requestPermissions(String[] permissions, int requestCode) {
        getMvpView().requestPermissionsSafely(permissions,requestCode);
    }

    @Override
    public void onPermissionsResult(boolean is_Granted) {

    }

    @Override
    public boolean hasPermission(String perm) {
        return getMvpView().hasPermission(perm);
    }

    @Override
    public void addLocationToDB(com.example.mohamedelnhrawy.Base.data.db.model.Location location) {
        getMvpView().showLoading();
        Long code = getDataManager().insertLocation(location);
        Log.e("insertion",""+code);
        getMvpView().hideLoading();
        if (code.toString() != null){
            getMvpView().showAddedSnackBar(R.string.location_added);
        }

    }

}
