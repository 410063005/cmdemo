package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_heavy_layout.*

class HeavyLayoutActivity2 : BaseActivity() {

    companion object {
        fun start(context: Context, colorItem: ColorItem) {
            val intent = Intent(context, HeavyLayoutActivity2::class.java)
            intent.putExtra("color_item", colorItem)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavy_layout)

        toggle.setOnCheckedChangeListener { _, isChecked ->
            Config.heavyLayout = isChecked
        }
    }

    fun forceLayout(view: View) {
        hlv1.requestLayout()
        hlv2.requestLayout()
        hlv3.requestLayout()
        hlv4.requestLayout()
        hlv5.requestLayout()
    }

}