package com.example.mohamedelnhrawy.Base.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mohamedelnhrawy.Base.di.ApplicationContext;
import com.example.mohamedelnhrawy.Base.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by mohamedelnhrawy on 1/25/19.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {
    private final SharedPreferences mPrefs;
    private static final String PREF_KEY_LOCATION_SELECTED_NUMBER = "PREF_KEY_LOCATION_SELECTED_NUMBER";



    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        this.mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public int getSelectedLcationCount() {
        return mPrefs.getInt(PREF_KEY_LOCATION_SELECTED_NUMBER,0);
    }

    @Override
    public void setSelectedLcationCount(int count) {
        mPrefs.edit().putInt(PREF_KEY_LOCATION_SELECTED_NUMBER, count).apply();
    }
}
