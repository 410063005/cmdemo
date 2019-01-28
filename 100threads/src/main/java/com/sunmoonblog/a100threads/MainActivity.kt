package com.sunmoonblog.a100threads

import android.os.*
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), Handler.Callback {

    private lateinit var doAsync: DoAsync

    // https://stackoverflow.com/questions/44525388/asynctask-in-android-with-kotlin
    class DoAsync(val handler: (String?) -> Unit) : AsyncTask<Void, String, Void>() {
        init {
            execute()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            while (!isCancelled) {
                publishProgress(threadStatus())
                Thread.sleep(1000)
            }
            return null
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            handler(values[0])
        }

        private fun threadStatus(): String {
            //val pro = Runtime.getRuntime().exec("cat /proc/${Process.myPid()}/status | grep Thread")
            val pro = Runtime.getRuntime().exec("cat /proc/${Process.myPid()}/status")
            return if (pro.waitFor() == 0) {
                pro.inputStream.bufferedReader().readText()
            } else {
                "Error"
            }
        }

    }

    override fun handleMessage(msg: Message?): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            for (i in 0 until 10) {
                createThread()
            }
        }

        button2.setOnClickListener {
            for (i in 0 until 20) {
                createThread()
            }
        }

        button3.setOnClickListener {
            for (i in 0 until 50) {
                createThread()
            }
        }

        doAsync = DoAsync {
            threadStatus.text = fmt(it)
        }

    }

    private fun fmt(str: String?): CharSequence? {
        if (str == null) {
            return str
        }

        val start = str.indexOf("Threads:")
        val end = str.indexOf("SigQ:")
        if (start != -1 && end != -1) {
            val ss = SpannableString(str)
            val bb = ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAccent))
            ss.setSpan(bb, start, end, 0)
            return ss
        }
        return str
    }

    override fun onDestroy() {
        super.onDestroy()
        doAsync.cancel(false)
    }

    private fun createThread() {
        thread {
            while (!isDestroyed) {
                Thread.sleep(3000)
            }
        }
    }
}
