package com.example.mohamedelnhrawy.Base.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.mohamedelnhrawy.Base.constants.DBConstant;
import com.example.mohamedelnhrawy.Base.data.db.model.Location;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by mohamedelnhrawy on 1/23/19.
 */

@Dao
public interface LocationDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Location... locations);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertLocation(Location item);

    @Query("SELECT * FROM " + DBConstant.LOCATIONS_TABLE_NAME)
    Flowable<List<Location>> getAll();

    @Delete
    int delete (Location location);

}
