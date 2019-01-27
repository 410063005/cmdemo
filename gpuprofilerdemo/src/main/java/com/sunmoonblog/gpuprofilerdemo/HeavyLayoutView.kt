package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import java.util.concurrent.TimeUnit

class HeavyLayoutView @JvmOverloads constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attributes, defStyleAttr) {

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (Config.heavyLayout) {
            TimeUnit.MILLISECONDS.sleep(Config.heavyLayoutDelay)
        }
    }

    /*override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (Config.heavyLayout) {
            TimeUnit.MILLISECONDS.sleep(Config.heavyLayoutDelay)
        }
    }*/
}