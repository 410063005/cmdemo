package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.graphics.Canvas
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import java.util.concurrent.TimeUnit

class HeavyDrawView @JvmOverloads constructor(context: Context,
                                              attributes: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attributes, defStyleAttr) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(ContextCompat.getColor(context, R.color.color_ececec))
        if (Config.heavyDraw) {
            TimeUnit.MILLISECONDS.sleep(Config.heavyDrawDelay)
        }
    }
}