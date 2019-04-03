package com.sunmoonblog.roomdemo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.sunmoonblog.roomdemo.demo2.Book;
import com.sunmoonblog.roomdemo.demo2.BookDao;
import com.sunmoonblog.roomdemo.demo2.User;
import com.sunmoonblog.roomdemo.demo2.UserDao;

@Database(entities = {Person.class, User.class, Book.class}, version = 3, exportSchema = false)
@TypeConverters({DateConvert.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract PersonDao getPersonDao();

    public abstract UserDao getUserDao();

    public abstract BookDao getBookDao();
}
