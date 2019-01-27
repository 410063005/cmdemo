package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Choreographer
import kotlinx.android.synthetic.main.activity_heavy_misc.*

class HeavyMiscActivity : BaseActivity() {

    private var running: Boolean = false

    companion object {
        fun start(context: Context, colorItem: ColorItem) {
            val intent = Intent(context, HeavyMiscActivity::class.java)
            intent.putExtra("color_item", colorItem)
            context.startActivity(intent)
        }
    }


    object FPSMeasuringCallback : Choreographer.FrameCallback {
        val choreographer = Choreographer.getInstance()!!
        override fun doFrame(frameTimeNanos: Long) {
            // Log.i("HeavyMainTaskActivity", frameTimeNanos.toString())
            if (Config.heavyMisc) {
                Thread.sleep(Config.heavyMiscDelay)
            }
            choreographer.postFrameCallback(this@FPSMeasuringCallback)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (running) {
            FPSMeasuringCallback.choreographer.removeFrameCallback(FPSMeasuringCallback)
            running = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavy_misc)

        clickMe.setOnClickListener {
            running = if (running) {
                FPSMeasuringCallback.choreographer.removeFrameCallback(FPSMeasuringCallback)
                clickMe.text = "启动"
                false
            } else {
                FPSMeasuringCallback.choreographer.postFrameCallback(FPSMeasuringCallback)
                clickMe.text = "停止"
                true
            }
        }

        toggle.setOnCheckedChangeListener { _, isChecked ->
            Config.heavyMisc = isChecked
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = NormalAdapter()
        rv.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
    }

    class NormalAdapter : CommonAdapter(R.layout.list_item_normal)

}
