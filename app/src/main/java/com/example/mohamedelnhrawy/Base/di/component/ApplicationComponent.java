package com.example.mohamedelnhrawy.Base.di.component;

import android.app.Application;
import android.content.Context;

import com.example.mohamedelnhrawy.Base.MvpApp;
import com.example.mohamedelnhrawy.Base.data.DataManager;
import com.example.mohamedelnhrawy.Base.data.db.AppDB;
import com.example.mohamedelnhrawy.Base.data.db.DbHelper;
import com.example.mohamedelnhrawy.Base.data.db.LocationDao;
import com.example.mohamedelnhrawy.Base.di.ApplicationContext;
import com.example.mohamedelnhrawy.Base.di.module.ApplicationModule;
import com.example.mohamedelnhrawy.Base.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MvpApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

}
