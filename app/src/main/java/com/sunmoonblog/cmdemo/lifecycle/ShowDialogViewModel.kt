package com.sunmoonblog.cmdemo.lifecycle

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ShowDialogViewModel(private val liveData: MutableLiveData<Int>) : ViewModel() {

    fun getLiveData(): LiveData<Int> {
        return liveData
    }

    fun showDf() {
        liveData.value = 1
    }

    fun haveShowed() {
        liveData.value = null
    }
}

object ShowDialogViewModelFactory : ViewModelProvider.Factory {

    private val liveData = MutableLiveData<Int>()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ShowDialogViewModel(liveData) as T
}