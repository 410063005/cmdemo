package com.sunmoonblog.gpuprofilerdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.sunmoonblog.gpuprofilerdemo.databinding.ListItemColorBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var colorData: ArrayList<ColorItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorData = ArrayList()
        val ids = resources.getIntArray(R.array.ids)
        val colors = resources.getIntArray(R.array.colors)
        val titles = resources.getStringArray(R.array.titles)
        val descs = resources.getStringArray(R.array.descs)
        for (i in 0 until colors.size) {
            colorData.add(ColorItem(ids[i], colors[i], titles[i], descs[i]))
        }

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = ColorRvAdapter()
    }

    inner class ColorRvAdapter : RecyclerView.Adapter<ColorRvHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ColorRvHolder {
            val binding = ListItemColorBinding.inflate(layoutInflater, p0, false)
            return ColorRvHolder(binding.root, binding)
        }

        override fun getItemCount() = colorData.size

        override fun onBindViewHolder(p0: ColorRvHolder, p1: Int) {
            p0.binding.item = colorData[p1]
            p0.binding.executePendingBindings()
            p0.binding.root.setOnClickListener {
                if (p0.adapterPosition != -1) {
                    Dispatcher.dispatch(this@MainActivity, colorData[p0.adapterPosition])
                }
            }
        }

    }

    class ColorRvHolder(itemView: View, val binding: ListItemColorBinding) : RecyclerView.ViewHolder(itemView)
}


