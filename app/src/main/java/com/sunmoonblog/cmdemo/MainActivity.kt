package com.sunmoonblog.cmdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sunmoonblog.cmdemo.layout.ClAnimationActivity
import com.sunmoonblog.cmdemo.layout.ClAnimationActivity2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clAnimation(view : View) {
        ClAnimationActivity.launchActivity(this)
    }

    fun clAnimation2(view : View) {
        ClAnimationActivity2.launchActivity(this)
    }
}
