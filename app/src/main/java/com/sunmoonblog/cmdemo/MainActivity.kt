package com.sunmoonblog.cmdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sunmoonblog.cmdemo.bitmap.BlurUIActivity
import com.sunmoonblog.cmdemo.databinding.DataBindingAdapterActivity
import com.sunmoonblog.cmdemo.databinding.DataBindingDemo2Activity
import com.sunmoonblog.cmdemo.databinding.DataBindingDemoActivity
import com.sunmoonblog.cmdemo.layout.ClAnimationActivity
import com.sunmoonblog.cmdemo.layout.ClAnimationActivity2
import com.sunmoonblog.cmdemo.layout.ClAnimationParallaxActivity
import com.sunmoonblog.cmdemo.layout.ClAnimationSimpleActivity
import com.sunmoonblog.cmdemo.lifecycle.ForegroundMonitorActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clBlur(view: View) {
        BlurUIActivity.start(this)
    }

    fun clDataBindingAdapter(view: View) {
        DataBindingAdapterActivity.start(this)
    }

    fun clDataBindingViewModel(view: View) {
        DataBindingDemo2Activity.start(this)
    }

    fun clDataBinding(view: View) {
        DataBindingDemoActivity.start(this)
    }

    fun clProcessLifecycleOwner(view: View) {
        ForegroundMonitorActivity.start(this)
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
