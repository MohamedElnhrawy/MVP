package com.example.mohamedelnhrawy.Base.data.network;

import com.example.mohamedelnhrawy.Base.BuildConfig;
import com.example.mohamedelnhrawy.Base.data.network.model.Place;
import com.example.mohamedelnhrawy.Base.data.network.model.PlaceRequest;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Observable<Place> getPlaceData(PlaceRequest request) {
        return Rx2AndroidNetworking.post(BuildConfig.BASE_URL)
                .addQueryParameter(request)
                .build()
                .getObjectObservable(Place.class);
    }
}
