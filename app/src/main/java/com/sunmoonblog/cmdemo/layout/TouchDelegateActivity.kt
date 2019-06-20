package com.sunmoonblog.cmdemo.layout

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.TouchDelegate
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_touch_delegate.*

class TouchDelegateActivity : AppCompatActivity() {

    private var num = 0

    private var touchAreaWidth = 0
    private var touchAreaHeight = 0

    private val action: Runnable = Runnable {
        touchAreaWidth = imageButton.width
        touchAreaHeight = imageButton.height

        // The bounds for the delegate view (an ImageButton
        // in this example)
        val delegateArea = Rect()

        imageButton.apply {
            isEnabled = true
            setOnClickListener {
                Toast.makeText(
                        this@TouchDelegateActivity,
                        "Touch occurred within ImageButton touch region.",
                        Toast.LENGTH_SHORT
                ).show()
            }
            getHitRect(delegateArea)
        }

        // Extend the touch area of the ImageButton beyond its bounds
        // on the right and bottom.
        delegateArea.right += 20 * num
        delegateArea.bottom += 20 * num

        // Update UI
        updateBorder(20 * num)

        // Sets the TouchDelegate on the parent view, such that touches
        // within the touch delegate bounds are routed to the child.
        (imageButton.parent as? View)?.apply {
            // Instantiate a TouchDelegate.
            // "delegateArea" is the bounds in local coordinates of
            // the containing view to be mapped to the delegate view.
            // "myButton" is the child view that should receive motion
            // events.
            touchDelegate = TouchDelegate(delegateArea, imageButton)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_delegate)


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                num = progress
                cl_parent.post(action)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        // Post in the parent's message queue to make sure the parent
        // lays out its children before you call getHitRect()
        cl_parent.post(action)
    }

    private fun updateBorder(delta : Int) {
        val p = touch_area.layoutParams
        if (p != null) {
            p.width = touchAreaWidth + delta
            p.height = touchAreaHeight + delta
            touch_area.layoutParams = p
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, TouchDelegateActivity::class.java)
            context.startActivity(intent)
        }
    }
}