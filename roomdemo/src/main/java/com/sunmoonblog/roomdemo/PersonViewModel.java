package com.sunmoonblog.roomdemo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Date;
import java.util.List;

public class PersonViewModel extends ViewModel {

    private PersonDao mPersonDao;

    private MutableLiveData<List<Person>> mLiveDataAllPerson = new MutableLiveData<>();

    public PersonViewModel() {
    }

    public LiveData<List<Person>> getLiveDataAllPerson() {
        return mLiveDataAllPerson;
    }

    public void getAllPerson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiveDataAllPerson.postValue(mPersonDao.getAll());
            }
        }).start();
    }

    public LiveData<List<Person>> getAllPerson2() {
        return mPersonDao.getAll2();
    }

    public LiveData<List<Person>> getAllPerson(Date from, Date to) {
        return mPersonDao.getAllPerson(from, to);
    }

    public void setPersonDao(PersonDao dao) {
        mPersonDao = dao;
    }

    public void delete(final Person person) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPersonDao.delete(person);
            }
        }).start();
    }
}
