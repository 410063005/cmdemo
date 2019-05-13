package com.sunmoonblog.cmdemo.bitmap

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunmoonblog.cmdemo.R
import com.sunmoonblog.cmdemo.databinding.ListItemBlurUiBinding
import java.util.*

val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BlurUIItem>() {
    override fun areItemsTheSame(p0: BlurUIItem, p1: BlurUIItem): Boolean {
        return p0.title == p1.title && p0.imageUrl == p1.imageUrl
    }

    override fun areContentsTheSame(p0: BlurUIItem, p1: BlurUIItem): Boolean {
        return p0.title == p1.title && p0.imageUrl == p1.imageUrl
    }

}

class BlurUIAdapter(diffCallback: DiffUtil.ItemCallback<BlurUIItem>) : ListAdapter<BlurUIItem, BlurUIViewHolder>(diffCallback) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BlurUIViewHolder {
        val binding = DataBindingUtil.inflate<ListItemBlurUiBinding>(LayoutInflater.from(p0.context),
                R.layout.list_item_blur_ui, p0, false)
        return BlurUIViewHolder(binding)
    }

    override fun onBindViewHolder(p0: BlurUIViewHolder, p1: Int) = p0.bind(getItem(p1))

}

class BlurUIViewHolder(private val binding: ListItemBlurUiBinding) : RecyclerView.ViewHolder(binding.root) {

    val rand = Random()

    fun bind(item: BlurUIItem) {
        binding.item = item
        binding.root.setBackgroundColor(Color.rgb(rand(), rand(), rand()))
        binding.executePendingBindings()
    }

    private fun rand() = rand.nextInt(256)

}