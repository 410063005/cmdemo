package com.sunmoonblog.cmdemo.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log

class MyLogger {

    companion object {
        private const val TAG = "MyLogger"
    }

    fun logCreate() {
        Log.d(TAG, "Activity created")
    }

    fun logStart() {
        Log.d(TAG, "Activity started")
    }

    fun logResume() {
        Log.d(TAG, "Activity resumed")
    }

    fun logPause() {
        Log.d(TAG, "Activity will pause")
    }

    fun logStop() {
        Log.d(TAG, "Activity will stop")
    }

    fun logDestroy() {
        Log.d(TAG, "Activity will be destroyed")
    }
}

class MyLifeCycleObserver(private val lifeCycle: Lifecycle, private val logger: MyLogger) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun logCreate() {
        logger.logCreate()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun logStart() {
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            logger.logStart()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun logResume() {
        logger.logResume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun logPause() {
        logger.logPause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun logStop() {
        logger.logStop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun logDestroy() {
        if (lifeCycle.currentState.isAtLeast(Lifecycle.State.DESTROYED)) {
            logger.logDestroy()
        }
    }
}
