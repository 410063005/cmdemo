package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import java.util.*

class HeavyIssueView @JvmOverloads constructor(context: Context,
                                               attributes: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attributes, defStyleAttr) {

    val p = Paint().apply {
        color = ContextCompat.getColor(context, R.color.color1)
        strokeWidth = 4f
    }
    private val arr = FloatArray(2000)

    private val rand = Random()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (Config.heavyIssue) {
            for (i in 0 until 1000) {
                canvas?.drawPoint(rand.nextInt(width).toFloat(), rand.nextInt(height).toFloat(), p)
            }
        } else {
            for (i in 0 until 1000 step 2) {
                arr[i] = rand.nextInt(width).toFloat()
                arr[i + 1] = rand.nextInt(height).toFloat()
            }
            canvas?.drawPoints(arr, p)
        }
    }
}