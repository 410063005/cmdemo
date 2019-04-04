package com.sunmoonblog.roomdemo

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class MyViewPager(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

    private var swipeEnabled = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (swipeEnabled) {
            return super.onInterceptTouchEvent(ev)
        }
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (swipeEnabled) {
            return super.onTouchEvent(ev)
        }
        return false
    }
}