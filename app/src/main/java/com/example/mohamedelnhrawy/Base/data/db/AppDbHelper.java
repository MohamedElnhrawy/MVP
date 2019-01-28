package com.example.mohamedelnhrawy.Base.data.db;

import com.example.mohamedelnhrawy.Base.data.db.model.Location;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mohamedelnhrawy on 1/24/19.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private AppDB database;

    @Inject
    public AppDbHelper(AppDB database) {
        this.database = database;
    }


    @Override
    public List<Long> insertAll(Location... locations) {
         return  database.locationDao().insertAll(locations);
    }

    @Override
    public Long insertLocation(Location item) {
        return database.locationDao().insertLocation(item);
    }

    @Override
    public Flowable<List<Location>> getAll() {
        return database.locationDao().getAll();
    }

    @Override
    public int delete(Location location) {
        return database.locationDao().delete(location);
    }
}
