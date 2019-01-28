package com.example.mohamedelnhrawy.Base.data.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.example.mohamedelnhrawy.Base.data.db.model.Location;

import javax.inject.Inject;

/**
 * Created by mohamedelnhrawy on 1/23/19.
 */

@Database(entities = {Location.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    public abstract LocationDao locationDao();


}
