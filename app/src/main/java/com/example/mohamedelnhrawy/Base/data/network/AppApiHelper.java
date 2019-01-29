package com.example.mohamedelnhrawy.Base.data.network;

import com.example.mohamedelnhrawy.Base.BuildConfig;
import com.example.mohamedelnhrawy.Base.data.network.model.Place;
import com.example.mohamedelnhrawy.Base.data.network.model.PlaceRequest;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

import static com.example.mohamedelnhrawy.Base.util.AppConstants.API_PLACE_DATA_ENDPOINT;

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
        HashMap<String,String> map =new HashMap<>();
        map.put("lat",request.getLat());
        map.put("lon",request.getLon());
        map.put("cnt",request.getCnt());
        map.put("appid",request.getAppid());

        return Rx2AndroidNetworking.get(BuildConfig.BASE_URL+API_PLACE_DATA_ENDPOINT)
                .addQueryParameter(map)
                .build()
                .getObjectObservable(Place.class);


    }
}
