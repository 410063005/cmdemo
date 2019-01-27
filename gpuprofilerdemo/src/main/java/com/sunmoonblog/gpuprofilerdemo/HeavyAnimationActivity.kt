package com.sunmoonblog.gpuprofilerdemo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_heavy_draw.*
import java.util.concurrent.TimeUnit

class HeavyAnimationActivity : BaseActivity() {

    companion object {
        fun start(context: Context, colorItem: ColorItem) {
            val intent = Intent(context, HeavyAnimationActivity::class.java)
            intent.putExtra("color_item", colorItem)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavy_draw)

        val textView = TextView(this)
        textView.text = "点我启动一个低效动画 ^-^"
        textView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply {
            leftMargin = 16
            topMargin = 500
        }
        content.addView(textView)

        textView.setOnClickListener {
            textView.animate().x(500f)
                    .setDuration(2000L)
                    .setInterpolator {
                        if (Config.heavyAnimate) {
                            TimeUnit.MILLISECONDS.sleep(Config.heavyAnimateDelay)
                        }
                        return@setInterpolator it
                    }
                    .setListener(object : AnimatorListenerAdapter() {

                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            textView.x = 0f
                        }

                    }).start()
        }

        toggle.setOnCheckedChangeListener { _, isChecked ->
            Config.heavyAnimate = isChecked
        }
    }

}