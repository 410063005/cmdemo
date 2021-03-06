package com.sunmoonblog.gpuprofilerdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_heavy_draw.*

class HeavyLayoutActivity : BaseActivity() {

    companion object {
        fun start(context: Context, colorItem: ColorItem) {
            val intent = Intent(context, HeavyLayoutActivity::class.java)
            intent.putExtra("color_item", colorItem)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heavy_draw)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = IssueLayoutAdapter()
        rv.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        toggle.setOnCheckedChangeListener { _, isChecked ->
            Config.heavyLayout = isChecked
        }
    }

    class IssueLayoutAdapter : CommonAdapter(R.layout.list_item_heavy_layout)
}