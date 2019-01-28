package com.example.mohamedelnhrawy.Base.ui.details;

import com.example.mohamedelnhrawy.Base.data.db.model.Location;
import com.example.mohamedelnhrawy.Base.ui.base.MvpPresenter;

/**
 * Created by mohamedelnhrawy on 1/28/19.
 */

public interface DetailsMvpPresenter<V extends DetailsMvpView> extends MvpPresenter<V> {
    void getLocationData(Location location);
}
