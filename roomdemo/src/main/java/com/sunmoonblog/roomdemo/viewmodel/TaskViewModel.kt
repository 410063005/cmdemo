package com.sunmoonblog.roomdemo.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sunmoonblog.roomdemo.App
import com.sunmoonblog.roomdemo.Person
import java.util.*
import kotlin.concurrent.thread

class TaskViewModel(app: Application) : AndroidViewModel(app) {

    private val personDao = (app as App).appDatabase.personDao
    private val mLiveDataAllPerson = MutableLiveData<List<Person>>()

    fun addTask(person: Person, block: (result: Boolean) -> Unit) {
        thread {
            personDao.insertAll(person)
            block(true)
        }
    }

    fun getLiveDataAllPerson(): LiveData<List<Person>> {
        return mLiveDataAllPerson
    }

    fun getAllPerson() {
        thread { mLiveDataAllPerson.postValue(personDao.all) }
    }

    fun getAllPerson2(): LiveData<List<Person>> {
        return personDao.all2
    }

    fun getAllPerson(from: Date, to: Date): LiveData<List<Person>> {
        return personDao.getAllPerson(from, to)
    }

    fun delete(person: Person) {
        thread { personDao.delete(person) }
    }
}