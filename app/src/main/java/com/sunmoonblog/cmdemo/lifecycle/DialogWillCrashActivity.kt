package com.sunmoonblog.cmdemo.lifecycle

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_dialog_will_crash.*

class DialogWillCrashActivity : AppCompatActivity() {

    private val liveData = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_will_crash)

        // Home 键将 DialogWillCrashActivity 切到后台时, 这里会有 crash
        button.setOnClickListener {

            button.postDelayed({
                Toast.makeText(this@DialogWillCrashActivity, "button message", Toast.LENGTH_SHORT).show()
                showMyDialogFragment()
            }, 3000)

        }

        // Home 键将 DialogWillCrashActivity 切到后台时, 可以使用 LiveData 避免 crash
        // 一个额外的好处是, DialogWillCrashActivity 重新切换到前台时, 还能正常弹框
        subscribeUi()
        button2.setOnClickListener {

            button2.postDelayed({
                Toast.makeText(this@DialogWillCrashActivity, "button2 message", Toast.LENGTH_SHORT).show()
                liveData.value = 1
            }, 3000)

        }

    }

    private fun showMyDialogFragment() {
        val d = MyDialogFragment()
        d.show(supportFragmentManager, "test")
    }

    private fun subscribeUi() {
        liveData.observe(this, Observer {
            if (it != null) {
                showMyDialogFragment()
            }
        })
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, DialogWillCrashActivity::class.java)
            context.startActivity(intent)
        }
    }
}