package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_heavy_issue.*

class HeavyIssueActivity : BaseActivity() {

    companion object {
        fun start(context: Context, colorItem: ColorItem) {
            val intent = Intent(context, HeavyIssueActivity::class.java)
            intent.putExtra("color_item", colorItem)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavy_issue)

        toggle.setOnCheckedChangeListener { _, isChecked ->
            Config.heavyIssue = isChecked
        }

        update.setOnClickListener {
            issueView.invalidate()
        }
    }

}