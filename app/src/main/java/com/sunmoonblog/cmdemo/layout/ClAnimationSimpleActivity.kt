package com.sunmoonblog.cmdemo.layout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_cl_animation_simple.*

class ClAnimationSimpleActivity : AppCompatActivity() {

    var set = false
    var backupH : Int = 0
    var backupW : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cl_animation_simple)
    }

    fun startAnimation(view: View) {
        set = if (set) {
            TransitionManager.beginDelayedTransition(findViewById(R.id.cl))
            textView.minHeight = backupH
            textView.minWidth = backupW
            false
        } else {
            backupH = textView.minHeight
            backupW = textView.minWidth
            TransitionManager.beginDelayedTransition(findViewById(R.id.cl))
            textView.minHeight = 100.dp()
            textView.minWidth = 200.dp()
            true
        }
    }

    companion object {
        fun launchActivity(context: Context) {
            val intent = Intent(context, ClAnimationSimpleActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun Int.dp(): Int {
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), dm).toInt()
    }
}

