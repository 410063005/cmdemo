package com.sunmoonblog.roomdemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.sunmoonblog.roomdemo.*
import kotlinx.android.synthetic.main.activity_collect.*

class CollectActivity : AppCompatActivity(), KeyboardMonitor {

    private var mPendingOpen: Boolean = false
    private var mPendingClose: Boolean = false

    companion object {

        private const val TAG = "CollectActivity"

        fun start(context: Context) {
            val starter = Intent(context, CollectActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        listenKeyboardVisibility(findViewById(R.id.content))

        fab.setOnClickListener {
            showSoftKeyboard(editText)
        }

        val adapter = MyFragmentPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter

        add.setOnClickListener {
            hideSoftKeyboard(editText)
            val text = editText.text.toString().trim { it <= ' ' }
            if (text.isEmpty()) {
                toast("内容为空")
            } else {
                addTask(text)
            }
            editText.setText("")
        }

        navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    view_pager.setCurrentItem(0, false)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_dashboard -> {
                    view_pager.setCurrentItem(1, false)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_notifications -> {
                    view_pager.setCurrentItem(2, false)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun addTask(text: String) {
        val dao = (application as App).appDatabase.personDao
        Thread(Runnable {
            dao.insertAll(Person(text, 20))
            runOnUiThread { toast("添加数据成功") }
        }).start()
    }

    override fun onKeyboardOpen() {
        if (mPendingOpen) {
            return
        }
        mPendingOpen = true

        Log.i(TAG, "onKeyboardOpen: ")

        ll_input.visibility = View.VISIBLE
        mPendingOpen = false
    }

    override fun onKeyboardClose() {
        if (mPendingClose) {
            return
        }
        mPendingClose = true

        Log.i(TAG, "onKeyboardClose: ")

        ll_input.visibility = View.INVISIBLE
        mPendingClose = false
    }

}
