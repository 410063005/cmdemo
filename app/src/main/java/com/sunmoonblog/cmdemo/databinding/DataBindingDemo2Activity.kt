package com.sunmoonblog.cmdemo.databinding

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_data_binding_demo2.*

class DataBindingDemo2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_data_binding_demo2)

        val binding = DataBindingUtil.setContentView<ActivityDataBindingDemo2Binding>(this, R.layout.activity_data_binding_demo2)
        val myViewModel = MyViewModel()
        binding.myViewModel = myViewModel

        // 给 binding 设置 lifecycle owner 后才能对 live data 进行数据绑定
        binding.setLifecycleOwner(this)

        editText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // myViewModel.message = s.toString()

                // binding.myViewModel?.let {
                //    it.message = s.toString()
                // }

                // binding.message = s.toString()

                // myViewModel.message = s.toString()

                // myViewModel.message2.set(s.toString())
                myViewModel.liveMessage.value = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        myViewModel.command.observe(this, Observer {
            Toast.makeText(this, it!!, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, DataBindingDemo2Activity::class.java)
            context.startActivity(intent)
        }
    }
}