package com.example.mohamedelnhrawy.Base.data;

import android.content.Context;

import com.example.mohamedelnhrawy.Base.data.db.DbHelper;
import com.example.mohamedelnhrawy.Base.data.db.model.Location;
import com.example.mohamedelnhrawy.Base.data.network.model.Place;
import com.example.mohamedelnhrawy.Base.data.network.model.PlaceRequest;
import com.example.mohamedelnhrawy.Base.data.network.ApiHelper;
import com.example.mohamedelnhrawy.Base.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mohamedelnhrawy on 1/21/19.
 */

@Singleton
public class AppDataManager implements DataManager  {
    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final DbHelper dbHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper,DbHelper dbHelper) {
        this.mContext = context;
        mApiHelper = apiHelper;
        this.dbHelper=dbHelper;
    }

    @Override
    public Observable<Place> getPlaceData(PlaceRequest request) {
        return mApiHelper.getPlaceData(request);
    }

    @Override
    public List<Long> insertAll(Location... items) {
        return dbHelper.insertAll(items);
    }

    @Override
    public Long insertLocation(Location item) {

        return dbHelper.insertLocation(item);
    }

    @Override
    public Flowable<List<Location>> getAll() {
        return dbHelper.getAll();
    }

    @Override
    public int delete(Location location) {

       return dbHelper.delete(location);
    }
}
