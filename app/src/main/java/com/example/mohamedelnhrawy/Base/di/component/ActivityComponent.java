package com.example.mohamedelnhrawy.Base.di.component;

import com.example.mohamedelnhrawy.Base.di.PerActivity;
import com.example.mohamedelnhrawy.Base.di.module.ActivityModule;
import com.example.mohamedelnhrawy.Base.ui.addcity.AddCityActivity;
import com.example.mohamedelnhrawy.Base.ui.details.DetailsActivity;
import com.example.mohamedelnhrawy.Base.ui.main.MainActivity;
import com.example.mohamedelnhrawy.Base.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);
    void inject(MainActivity activity);
    void inject(AddCityActivity activity);
    void inject(DetailsActivity activity);


}
