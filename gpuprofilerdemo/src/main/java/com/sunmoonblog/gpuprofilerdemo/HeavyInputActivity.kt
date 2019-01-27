package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_heavy_input.*
import java.util.concurrent.TimeUnit

class HeavyInputActivity : BaseActivity() {

    companion object {
        fun start(context: Context, colorItem: ColorItem) {
            val intent = Intent(context, HeavyInputActivity::class.java)
            intent.putExtra("color_item", colorItem)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavy_input)

        clickMe.setOnClickListener {
            if (Config.heavyInput) {
                TimeUnit.MILLISECONDS.sleep(Config.heavyInputDelay)
            }
        }

        toggle.setOnCheckedChangeListener { _, isChecked ->
            Config.heavyInput = isChecked
        }
    }

}