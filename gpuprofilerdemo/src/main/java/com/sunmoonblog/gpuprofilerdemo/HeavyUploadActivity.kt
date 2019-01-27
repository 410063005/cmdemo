package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_heavy_upload.*
import java.util.*

class HeavyUploadActivity : BaseActivity() {

    companion object {
        fun start(context: Context, colorItem: ColorItem) {
            val intent = Intent(context, HeavyUploadActivity::class.java)
            intent.putExtra("color_item", colorItem)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavy_upload)

        clickMe.setOnClickListener {
            image.setImageBitmap(createRandomBitmap())
        }
    }

    private fun createRandomBitmap(): Bitmap {
        return Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888).apply {
            val canvas = Canvas(this)
            val rand = Random()
            canvas.drawColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)))
        }
    }
}