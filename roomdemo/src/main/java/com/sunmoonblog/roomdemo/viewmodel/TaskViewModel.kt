package com.sunmoonblog.roomdemo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.sunmoonblog.roomdemo.App
import com.sunmoonblog.roomdemo.Person
import kotlin.concurrent.thread

class TaskViewModel(app: Application) : AndroidViewModel(app) {

    private val personDao = (app as App).appDatabase.personDao

    fun addTask(person: Person, block: (result: Boolean) -> Unit) {
        thread {
            personDao.insertAll(person)
            block(true)
        }
    }
}