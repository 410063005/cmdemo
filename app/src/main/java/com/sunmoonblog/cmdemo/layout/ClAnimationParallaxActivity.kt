package com.sunmoonblog.cmdemo.layout

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.support.v7.app.AppCompatActivity
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_cl_animation_parallax.*
import kotlinx.android.synthetic.main.list_item_cl_animation_parallax_content.view.*

// ref https://github.com/ibhavikmakwana/Constraint-Layout-Animations/blob/master/app/src/main/java/com/keyframeanimationdemo/activity/parallax/ParallaxEffectActivity.kt
class ClAnimationParallaxActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cl_animation_parallax)

        layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv.layoutManager = layoutManager
        val imageAdapter = ImageAdapter()
        rv.adapter = imageAdapter

        PagerSnapHelper().attachToRecyclerView(rv)

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val offset = recyclerView.computeHorizontalScrollOffset()

                val first = layoutManager.findFirstVisibleItemPosition()
                val last = layoutManager.findLastVisibleItemPosition()

                // Log.i("ClAnimationParallax", "firstVisible=$first lastVisible=$last offset=$offset")
                if (first != -1 && last != -1) {
                    val w = recyclerView.width

                    for (index in first..last) {
                        val v = layoutManager.findViewByPosition(index)
                        if (v != null) {
                            val guideline = v.findViewById<Guideline>(R.id.guide)
                            val guidelineParams = guideline.layoutParams as ConstraintLayout.LayoutParams

                            val deltaPos = offset - index * w
                            val percent = deltaPos / w.toFloat()
                            val guidelinePercent = Math.max(0.3f, Math.min(0.7f, 0.5f - percent))
                            Log.i("ClAnimationParallax", "index=$index, deltaPos=$deltaPos percent=$percent, guidelinePercent=$guidelinePercent")
                            guidelineParams.guidePercent = guidelinePercent
                            guideline.layoutParams = guidelineParams
                        }
                    }

                }
            }
        })

        imageAdapter.submitList(testData())
    }

    class ImageAdapter : ListAdapter<ImageItem, ImageItemViewHolder>(DIFF) {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
                ImageItemViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item_cl_animation_parallax, p0, false))

        override fun onBindViewHolder(p0: ImageItemViewHolder, p1: Int) {
            p0.bind(getItem(p1))
        }

    }

    class ImageItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.image
        private val title = itemView.title
        private val subtitle = itemView.subtitle

        fun bind(item: ImageItem) {
            Glide.with(itemView.context).load(item.imageUrl).into(image)
            title.text = item.title
            subtitle.text = item.subtitle
        }

    }

    data class ImageItem(
            val imageUrl: String = "",
            val title: String = "No title",
            val subtitle: String = "No subtitle")

    companion object {

        val DIFF = object : DiffUtil.ItemCallback<ImageItem>() {
            override fun areItemsTheSame(p0: ImageItem, p1: ImageItem): Boolean {
                return p0 == p1
            }

            override fun areContentsTheSame(p0: ImageItem, p1: ImageItem): Boolean {
                return p0 == p1
            }

        }

        fun start(context: Context) {
            val intent = Intent(context, ClAnimationParallaxActivity::class.java)
            context.startActivity(intent)
        }

        fun testData() : List<ImageItem> {
            val data = ArrayList<ImageItem>()
            data.add(ImageItem("http://a.hiphotos.baidu.com/image/pic/item/5bafa40f4bfbfbedcb2d862a76f0f736afc31f6a.jpg", "叶子", "好看吗"))
            data.add(ImageItem("http://a.hiphotos.baidu.com/image/pic/item/b21c8701a18b87d6070d7970090828381f30fd16.jpg", "装bi男", "差评"))
            data.add(ImageItem("http://e.hiphotos.baidu.com/image/pic/item/5bafa40f4bfbfbed07213a2376f0f736afc31f75.jpg", "哈哈哈哈哈哈", "good"))
            data.add(ImageItem("http://g.hiphotos.baidu.com/image/pic/item/b812c8fcc3cec3fd9aafc522d888d43f869427d7.jpg", "who", "handsome"))
            data.add(ImageItem("http://a.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cea3aba0619c45d688d43f207c.jpg", "今天坐火车", "这里是山东?"))
            return data
        }
    }
}