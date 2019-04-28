package com.sunmoonblog.cmdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sunmoonblog.cmdemo.layout.ClAnimationActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clAnimation(view : View) {
        ClAnimationActivity.launchActivity(this)
    }
}
