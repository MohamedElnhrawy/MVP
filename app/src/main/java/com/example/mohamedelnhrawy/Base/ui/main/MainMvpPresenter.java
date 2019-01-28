package com.example.mohamedelnhrawy.Base.ui.main;

import com.example.mohamedelnhrawy.Base.ui.base.MvpPresenter;

/**
 * Created by mohamedelnhrawy on 1/22/19.
 */

public interface MainMvpPresenter  <V extends MainMvpView> extends MvpPresenter<V> {
    void getLocationsFromDB();
}
