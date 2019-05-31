package com.sunmoonblog.cmdemo.bitmap

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_bitmap_decode_perf.*
import kotlin.concurrent.thread

class BitmapDecodePerfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_decode_perf)

        val point = Point()
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getSize(point)
        windowManager.defaultDisplay.getMetrics(metrics)
        deviceScreen.text = "w,h=${point.x},${point.y}, w,h=${metrics.widthPixels},${metrics.heightPixels}, " +
                " density=${metrics.density}, densityDpi=${metrics.densityDpi}"

        deviceScreen.text

        // webp 性能
        button11.setOnClickListener {
            decodeBatch(listOf("kb_80.webp", "kb_90.webp", "kb_100.webp"), 20)
        }

        // png 性能
        button12.setOnClickListener {
            decodeBatch(listOf("kb_1.png", "kb_50.png", "kb_80.png", "kb_100.png", "kb_190.png", "kb_320.png"), 20)
        }

        buttonIcon.setOnClickListener {
            decode("kb_1.png", "1KB")
        }

        button13.setOnClickListener {
            decode("kb_50.png", "50KB")
        }

        button14.setOnClickListener {
            decode("kb_80.png", "80KB")
        }

        button15.setOnClickListener {
            decode("kb_100.png", "100KB")
        }

        button16.setOnClickListener {
            decode("kb_190.png", "190KB")
        }

        button17.setOnClickListener {
            decode("kb_320.png", "320KB", times = 20)

        }

        // 同一张图片 png 与 webp 对比
        button18.setOnClickListener {
            decodeBatch(listOf("jialuo.png", "jialuo_lossless.webp", "jialuo_lossy_75.webp"), 20)
        }

        // 同一张图片, 放在不同 hdpi 目录gh
        button19.setOnClickListener {
            decodeResource(R.drawable.splash_as_xhdpi, "splash_as_xhdpi")
            decodeResource(R.drawable.splash_as_xxhdpi, "splash_as_xxhdpi")
            decodeResource(R.drawable.splash_as_xxxhdpi, "splash_as_xxxhdpi")
        }

    }

    private fun decodeResource(id: Int, sizeDesc: String) {
        val t1 = System.nanoTime()
        val bmp = BitmapFactory.decodeResource(resources, id)
        val t2 = System.nanoTime()

        val mem = "%.1f MB".format(bmp.allocationByteCount / 1024.0 / 1024.0)
        Log.i("BitmapDecode", "decodeStream: file size $sizeDesc, mem $mem, costs ${t2 - t1} ns, " +
                "(w,h)=(${bmp.width}, ${bmp.height})")
    }

    private fun decode(name: String, sizeDesc: String, times: Int = 20) {
        thread {
            var mem = ""
            var count = 0L
            repeat(times) {
                assets.open(name).use {
                    val t1 = System.nanoTime()
                    val bmp = BitmapFactory.decodeStream(it)
                    val t2 = System.nanoTime()
                    count += (t2 - t1)
                    mem = "%.1f MB".format(bmp.allocationByteCount / 1024.0 / 1024.0)
                }
            }
            count = count.div(times)

            Log.i("BitmapDecode", "decodeStream: file size $name, mem $mem, costs $count ns")
        }
    }

    private fun decodeBatch(names: List<String>, times: Int = 20) {
        thread {
            names.forEach {name ->
                var mem = ""
                var count = 0L
                repeat(times) {
                    assets.open(name).use {
                        val t1 = System.nanoTime()
                        val bmp = BitmapFactory.decodeStream(it)
                        val t2 = System.nanoTime()
                        count += (t2 - t1)
                        mem = "%.1f MB".format(bmp.allocationByteCount / 1024.0 / 1024.0)
                    }
                }
                count = count.div(times)

                Log.i("BitmapDecode", "decodeStream: file size $name, mem $mem, costs $count ns")
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, BitmapDecodePerfActivity::class.java)
            context.startActivity(intent)
        }
    }
}