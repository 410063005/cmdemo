package com.sunmoonblog.gpuprofilerdemo

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            val colorItem = it.getParcelableExtra("color_item") as ColorItem
            title = colorItem.title
            supportActionBar?.setBackgroundDrawable(ColorDrawable(colorItem.color))
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        intent?.let {
            val colorItem = it.getParcelableExtra("color_item") as ColorItem
            findViewById<TextView>(R.id.desc)?.text = colorItem.desc
        }
    }

}

class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

abstract class CommonAdapter(private val layoutId : Int) : RecyclerView.Adapter<CommonViewHolder>() {

    private val data = (1..100).toList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonViewHolder {
        return CommonViewHolder(LayoutInflater.from(p0.context).inflate(layoutId, p0, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(p0: CommonViewHolder, p1: Int) {
    }

}