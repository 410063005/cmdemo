package com.sunmoonblog.roomdemo.demo2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)
}

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book)

    @Query("select * from book_table")
    fun getAllBooks(): List<Book>
}