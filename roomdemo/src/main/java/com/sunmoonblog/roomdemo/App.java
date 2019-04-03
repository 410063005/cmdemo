package com.sunmoonblog.roomdemo;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {

    private AppDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppDatabase = Room.databaseBuilder(this, AppDatabase.class, "cm-first-db")
                .fallbackToDestructiveMigration().build();
    }

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }
}
