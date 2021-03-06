package com.sunmoonblog.cmdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sunmoonblog.cmdemo.bitmap.BitmapDecodePerfActivity
import com.sunmoonblog.cmdemo.bitmap.BlurUIActivity
import com.sunmoonblog.cmdemo.databinding.DataBindingAdapterActivity
import com.sunmoonblog.cmdemo.databinding.DataBindingDemo2Activity
import com.sunmoonblog.cmdemo.databinding.DataBindingDemoActivity
import com.sunmoonblog.cmdemo.dlsym.LoadSoActivity
import com.sunmoonblog.cmdemo.layout.*
import com.sunmoonblog.cmdemo.lifecycle.DialogWillCrashActivity
import com.sunmoonblog.cmdemo.lifecycle.ForegroundMonitorActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clTouchEventDelegate(view: View) {
        TouchDelegateActivity.start(this)
    }

    fun clTouchEvent(view : View) {
        CmViewActivity.start(this)
    }

    fun clDlopen(view: View) {
        LoadSoActivity.start(this)
    }

    fun clBitmapDecodePerf(view: View) {
        BitmapDecodePerfActivity.start(this)
    }

    fun clLiveDataDialog(view: View) {
        DialogWillCrashActivity.start(this)
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
