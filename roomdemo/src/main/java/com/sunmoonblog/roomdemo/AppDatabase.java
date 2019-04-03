package com.sunmoonblog.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Person.class}, version = 2)
@TypeConverters({DateConvert.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract PersonDao getPersonDao();
}
