package com.sunmoonblog.cmdemo.databinding

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_data_binding_demo.*

class DataBindingDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDataBindingDemoBinding>(this,
                R.layout.activity_data_binding_demo)

        buttonUpdate.setOnClickListener {
            binding.message = binding.editText.text.toString()
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DataBindingDemoActivity::class.java))
        }
    }
}