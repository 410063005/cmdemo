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

class ClAnimationActivity2 : AppCompatActivity() {

    private lateinit var constraintLayout: ConstraintLayout
    private val applyConstraintSet = ConstraintSet()
    private val resetConstraintSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cl_animation2_init)

        constraintLayout = findViewById(R.id.cl)
        resetConstraintSet.clone(constraintLayout)
        applyConstraintSet.clone(this, R.layout.activity_cl_animation2)
    }

    fun onApplyClick(view: View) {
        TransitionManager.beginDelayedTransition(constraintLayout)

        applyConstraintSet.applyTo(constraintLayout)
    }

    fun onResetClick(view: View) {
        TransitionManager.beginDelayedTransition(constraintLayout);
        resetConstraintSet.applyTo(constraintLayout)
    }

    companion object {
        fun launchActivity(context: Context) {
            val intent = Intent(context, ClAnimationActivity2::class.java)
            context.startActivity(intent)
        }
    }

}