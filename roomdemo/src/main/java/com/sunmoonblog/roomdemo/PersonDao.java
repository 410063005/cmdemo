package com.sunmoonblog.roomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insertAll(Person ...persons);

    @Update
    void update(Person ...persons);

    @Query("select * from person")
    List<Person> getAll();

    @Query("select * from person order by id desc")
    LiveData<List<Person>> getAll2();

    @Query("select * from person where id == :id")
    LiveData<Person> getById(long id);

    @Delete
    void delete(Person person);

    @Query("select * from person where user_date >= :from and user_date <= :to order by id desc")
    LiveData<List<Person>> getAllPerson(Date from, Date to);
}
