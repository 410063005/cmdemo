package com.sunmoonblog.roomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insertAll(Person ...persons);

    @Query("select * from person")
    List<Person> getAll();

    @Query("select * from person where user_age in (:ages)")
    LiveData<List<Person>> getAll(List<Integer> ages);

    @Delete
    void delete(Person person);
}
