package com.sunmoonblog.cmdemo.databinding

import android.arch.lifecycle.MutableLiveData
import android.databinding.BaseObservable
import android.databinding.ObservableField

class MyViewModel : BaseObservable() {

    val command = MutableLiveData<String>()

    var message: String = "hello, data binding"

        set(value) {
            field = value
            //notifyPropertyChanged(BR.message)
            notifyChange()
        }

    var message2 : ObservableField<String> = ObservableField("hello, data binding")

    val liveMessage = MutableLiveData<String>()

    fun showToast() {
        command.value = "show_toast"
    }
}