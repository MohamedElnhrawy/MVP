package com.example.mohamedelnhrawy.Base.ui.main;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.example.mohamedelnhrawy.Base.data.DataManager;
import com.example.mohamedelnhrawy.Base.data.db.model.Location;
import com.example.mohamedelnhrawy.Base.ui.base.BasePresenter;
import com.example.mohamedelnhrawy.Base.util.rx.SchedulerProvider;
import com.patloew.rxlocation.RxLocation;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mohamedelnhrawy on 1/22/19.
 */

public class MainPresenter <V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager, RxLocation rxLocation, SchedulerProvider mSchedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, rxLocation, mSchedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getMvpView().setUpRecyclerView();
        getLocationsFromDB();
    }

    @Override
    public void getLocationsFromDB() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager().getAll().subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Location>>() {
                    @Override
                    public void accept(List<Location> locations) throws Exception {
                        getMvpView().updateRecyclerAdapter(locations);
                        getMvpView().hideLoading();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
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
