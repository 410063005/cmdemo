package com.sunmoonblog.cmdemo.lifecycle

import android.arch.lifecycle.ProcessLifecycleOwner
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_foreground_monitor.*

class ForegroundMonitorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_monitor)

        AppLifecycleObserver.init(application)

        buttonStartMonitor.setOnClickListener {
            ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleObserver)
        }

        buttonStopMonitor.setOnClickListener {
            ProcessLifecycleOwner.get().lifecycle.removeObserver(AppLifecycleObserver)
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ForegroundMonitorActivity::class.java))
        }
    }
}