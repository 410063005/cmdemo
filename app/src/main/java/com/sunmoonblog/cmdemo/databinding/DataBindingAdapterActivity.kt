package com.sunmoonblog.cmdemo.databinding

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sunmoonblog.cmdemo.R

class DataBindingAdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDataBindingAdapterBinding>(this,
                R.layout.activity_data_binding_adapter)
        binding.imageUrl = "http://e.hiphotos.baidu.com/image/pic/item/dc54564e9258d1092f7663c9db58ccbf6c814d30.jpg"
    }

    override fun onDestroy() {
        super.onDestroy()
        // force placeholder to be displayed
        Glide.get(this).clearMemory()
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, DataBindingAdapterActivity::class.java)
            context.startActivity(intent)
        }
    }
}