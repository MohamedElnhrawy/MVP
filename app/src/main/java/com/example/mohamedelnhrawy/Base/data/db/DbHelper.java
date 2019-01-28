package com.example.mohamedelnhrawy.Base.data.db;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mohamedelnhrawy.Base.constants.DBConstant;
import com.example.mohamedelnhrawy.Base.data.db.model.Location;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by mohamedelnhrawy on 1/24/19.
 */

public interface DbHelper  {
    List<Long> insertAll(Location... items);

    Long insertLocation(Location item);

    Flowable<List<Location>> getAll();

    int delete (Location location);
}
