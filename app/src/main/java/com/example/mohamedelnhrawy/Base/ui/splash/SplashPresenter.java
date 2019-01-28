package com.example.mohamedelnhrawy.Base.ui.splash;

import com.example.mohamedelnhrawy.Base.data.DataManager;
import com.example.mohamedelnhrawy.Base.ui.base.BasePresenter;
import com.example.mohamedelnhrawy.Base.util.rx.SchedulerProvider;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

public class SplashPresenter <V extends SplashMvpView> extends BasePresenter<V>  implements SplashMvpPresenter<V>{


    @Inject
    public SplashPresenter(DataManager dataManager, RxLocation rxLocation, SchedulerProvider mSchedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, rxLocation, mSchedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
        getMvpView().openMainActivity();
    }


}
