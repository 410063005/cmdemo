package com.sunmoonblog.cmdemo.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MyLifeCycleObserverTest {

    lateinit var lifeCycleObserver: MyLifeCycleObserver
    lateinit var lifeCycle: LifecycleRegistry
    val logger : MyLogger = mock(MyLogger::class.java)

    @Before
    fun setUp() {
        val lifeCycleOwner: LifecycleOwner = mock(LifecycleOwner::class.java)
        lifeCycle = LifecycleRegistry(lifeCycleOwner)
        lifeCycleObserver = MyLifeCycleObserver(lifeCycle, logger)
        lifeCycle.addObserver(lifeCycleObserver)

        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    @Test
    fun shouldLogStart() {
        lifeCycle.markState(Lifecycle.State.STARTED)
        verify(logger).logStart()
    }

    @Test
    fun shouldLogStop() {
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        lifeCycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        verify(logger).logStop()
    }
}