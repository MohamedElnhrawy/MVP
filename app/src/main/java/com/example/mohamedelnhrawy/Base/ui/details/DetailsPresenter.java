package com.example.mohamedelnhrawy.Base.ui.details;

import com.androidnetworking.error.ANError;
import com.example.mohamedelnhrawy.Base.BuildConfig;
import com.example.mohamedelnhrawy.Base.data.DataManager;
import com.example.mohamedelnhrawy.Base.data.db.model.Location;
import com.example.mohamedelnhrawy.Base.data.network.model.Place;
import com.example.mohamedelnhrawy.Base.data.network.model.PlaceRequest;
import com.example.mohamedelnhrawy.Base.ui.base.BasePresenter;
import com.example.mohamedelnhrawy.Base.util.rx.SchedulerProvider;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mohamedelnhrawy on 1/28/19.
 */

public class DetailsPresenter <V extends DetailsMvpView> extends BasePresenter<V>  implements DetailsMvpPresenter<V>{

    @Inject
    public DetailsPresenter(DataManager dataManager, RxLocation rxLocation, SchedulerProvider mSchedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, rxLocation, mSchedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getMvpView().getIntentData();
    }

    @Override
    public void getLocationData(Location location) {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager().getPlaceData(new PlaceRequest(location.getLocation_latitude(),
                location.getLocation_longitude(), BuildConfig.API_KEY)).subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(new Consumer<Place>() {
            @Override
            public void accept(Place place) throws Exception {
                if (!isViewAttached()) {
                    return;
                }

                getMvpView().hideLoading();
                getMvpView().updateUi(place);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (!isViewAttached()) {
                    return;
                }

                getMvpView().hideLoading();

                // handle the login error here
                if (throwable instanceof ANError) {
                    ANError anError = (ANError) throwable;
                    handleApiError(anError);
                }
            }
        }));
    }
}
