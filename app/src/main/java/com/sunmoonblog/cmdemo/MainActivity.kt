package com.sunmoonblog.cmdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sunmoonblog.cmdemo.layout.ClAnimationActivity
import com.sunmoonblog.cmdemo.layout.ClAnimationActivity2
import com.sunmoonblog.cmdemo.layout.ClAnimationParallaxActivity
import com.sunmoonblog.cmdemo.layout.ClAnimationSimpleActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clAnimationParallax(view: View) {
        ClAnimationParallaxActivity.start(this)
    }

    fun clAnimationSimple(view: View) {
        ClAnimationSimpleActivity.launchActivity(this)
    }

    fun clAnimation(view : View) {
        ClAnimationActivity.launchActivity(this)
    }

    fun clAnimation2(view : View) {
        ClAnimationActivity2.launchActivity(this)
    }
}
