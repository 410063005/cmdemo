package com.sunmoonblog.cmdemo.layout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.View
import com.sunmoonblog.cmdemo.R

class ClAnimationActivity : AppCompatActivity() {

    private lateinit var constraintLayout: ConstraintLayout
    private val applyConstraintSet = ConstraintSet()
    private val resetConstraintSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cl_animation)

        constraintLayout = findViewById(R.id.cl)
        resetConstraintSet.clone(constraintLayout)
        applyConstraintSet.clone(constraintLayout)
    }

    fun onApplyClick(view: View) {
        TransitionManager.beginDelayedTransition(constraintLayout);

        // 按钮靠左
        applyConstraintSet.setMargin(R.id.button, ConstraintSet.START,8)

        // 按钮居中
        applyConstraintSet.centerHorizontally(R.id.button2, R.id.cl)
        applyConstraintSet.setMargin(R.id.button2, ConstraintSet.START, 0)

        // 按钮宽度变化
        applyConstraintSet.constrainWidth(R.id.button3, 600)

        applyConstraintSet.setVisibility(R.id.button5, ConstraintSet.GONE)

        // 按钮占满屏幕
        applyConstraintSet.clear(R.id.button4)
        applyConstraintSet.connect(R.id.button4, ConstraintSet.START, R.id.cl, ConstraintSet.START, 0)
        applyConstraintSet.connect(R.id.button4, ConstraintSet.END, R.id.cl, ConstraintSet.END, 0)
        applyConstraintSet.connect(R.id.button4, ConstraintSet.TOP, R.id.button3, ConstraintSet.BOTTOM, 0)
        applyConstraintSet.connect(R.id.button4, ConstraintSet.BOTTOM, R.id.apply, ConstraintSet.TOP, 32)

        applyConstraintSet.applyTo(constraintLayout)
    }

    fun onResetClick(view: View) {
        TransitionManager.beginDelayedTransition(constraintLayout);
        resetConstraintSet.applyTo(constraintLayout)
    }


    companion object {
        fun launchActivity(context: Context) {
            val intent = Intent(context, ClAnimationActivity::class.java)
            context.startActivity(intent)
        }
    }
}