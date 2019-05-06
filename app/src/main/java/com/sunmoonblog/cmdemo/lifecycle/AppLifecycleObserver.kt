package com.sunmoonblog.cmdemo.lifecycle

import android.annotation.SuppressLint
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.Log
import android.widget.Toast

@SuppressLint("StaticFieldLeak")
object AppLifecycleObserver : LifecycleObserver {

    lateinit var context : Context

    fun init(context: Context) {
        this.context = context
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun foreground() {
        Log.i("AppLifecycleObserver", "goto foreground")
        Toast.makeText(context, "goto foreground", Toast.LENGTH_SHORT).show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun background() {
        Log.i("AppLifecycleObserver", "goto background")
        Toast.makeText(context, "goto background", Toast.LENGTH_SHORT).show()
    }
}