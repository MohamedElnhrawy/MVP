package com.example.mohamedelnhrawy.Base.ui.details;

import com.example.mohamedelnhrawy.Base.data.network.model.Place;
import com.example.mohamedelnhrawy.Base.ui.base.MvpView;

/**
 * Created by mohamedelnhrawy on 1/28/19.
 */

public interface DetailsMvpView extends MvpView {
    void getIntentData();
    void updateUi(Place place);
}
