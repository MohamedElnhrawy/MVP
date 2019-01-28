/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.mohamedelnhrawy.Base.di.module;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;


import com.example.mohamedelnhrawy.Base.data.db.AppDB;
import com.example.mohamedelnhrawy.Base.data.db.LocationDao;
import com.example.mohamedelnhrawy.Base.di.ActivityContext;
import com.example.mohamedelnhrawy.Base.di.ApplicationContext;
import com.example.mohamedelnhrawy.Base.di.PerActivity;
import com.example.mohamedelnhrawy.Base.ui.addcity.AddCityMvpPresenter;
import com.example.mohamedelnhrawy.Base.ui.addcity.AddCityMvpView;
import com.example.mohamedelnhrawy.Base.ui.addcity.AddCityPresenter;
import com.example.mohamedelnhrawy.Base.ui.details.DetailsMvpPresenter;
import com.example.mohamedelnhrawy.Base.ui.details.DetailsMvpView;
import com.example.mohamedelnhrawy.Base.ui.details.DetailsPresenter;
import com.example.mohamedelnhrawy.Base.ui.main.MainMvpPresenter;
import com.example.mohamedelnhrawy.Base.ui.main.MainMvpView;
import com.example.mohamedelnhrawy.Base.ui.main.MainPresenter;
import com.example.mohamedelnhrawy.Base.ui.splash.SplashMvpPresenter;
import com.example.mohamedelnhrawy.Base.ui.splash.SplashMvpView;
import com.example.mohamedelnhrawy.Base.ui.splash.SplashPresenter;
import com.example.mohamedelnhrawy.Base.util.AppConstants;
import com.example.mohamedelnhrawy.Base.util.rx.AppSchedulerProvider;
import com.example.mohamedelnhrawy.Base.util.rx.SchedulerProvider;
import com.patloew.rxlocation.RxLocation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    AddCityMvpPresenter<AddCityMvpView> provideAddCityPresenter(AddCityPresenter<AddCityMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    DetailsMvpPresenter<DetailsMvpView> provideDetailsPresenter(DetailsPresenter<DetailsMvpView> presenter){
        return presenter;
    }

    @Provides
    @PerActivity
    RxLocation provideRXLocation(Activity activity) {
        return new RxLocation(activity);
    }




}
