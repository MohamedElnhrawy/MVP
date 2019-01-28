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

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;


import com.example.mohamedelnhrawy.Base.BuildConfig;
import com.example.mohamedelnhrawy.Base.data.AppDataManager;
import com.example.mohamedelnhrawy.Base.data.DataManager;
import com.example.mohamedelnhrawy.Base.data.db.AppDB;
import com.example.mohamedelnhrawy.Base.data.db.AppDbHelper;
import com.example.mohamedelnhrawy.Base.data.db.DbHelper;
import com.example.mohamedelnhrawy.Base.data.db.LocationDao;
import com.example.mohamedelnhrawy.Base.data.network.ApiHelper;
import com.example.mohamedelnhrawy.Base.data.network.AppApiHelper;
import com.example.mohamedelnhrawy.Base.data.prefs.AppPreferencesHelper;
import com.example.mohamedelnhrawy.Base.data.prefs.PreferencesHelper;
import com.example.mohamedelnhrawy.Base.di.ApiInfo;
import com.example.mohamedelnhrawy.Base.di.ApplicationContext;
import com.example.mohamedelnhrawy.Base.di.DatabaseInfo;
import com.example.mohamedelnhrawy.Base.di.PreferenceInfo;
import com.example.mohamedelnhrawy.Base.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }




    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }



    @Provides
    @Singleton
    AppDB provideLocalDB(Application application) {
        return Room.databaseBuilder(application,
                AppDB.class, AppConstants.DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    LocationDao provideLocationDao(AppDB db) {
        return db.locationDao();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDB db) {
        return new AppDbHelper(db);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}
