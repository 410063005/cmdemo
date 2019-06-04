package com.sunmoonblog.cmdemo.dlsym

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_load_so.*

// logcat 中观察日志 HelloJni
class LoadSoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_so)

        buttonLoad.setOnClickListener {
            // Java 代码加载系统库, 预期结果: app crash
            System.load("/system/lib/libart.so")
        }

        buttonLoadLibrary.setOnClickListener {
            // Java 代码加载系统库, 预期结果: app crash
            System.loadLibrary("art")
        }

        buttonDlopen.setOnClickListener {
            // Native 代码加载系统库, 预期结果: 加载失败
            LoadArt.loadArt()
        }

        buttonFakeDlopen.setOnClickListener {
            // Native 代码加载系统库(使用 fake_dlsym), 预期结果: 加载成功
            LoadArt.fakeLoadArt()
        }

        buttonDlopenOwnSo.setOnClickListener {
            val soPath = makeSureSoExists()
            // Native 代码加载自带的库, 预期结果: 加载成功
            LoadArt.loadOwnSo(soPath)
        }

        buttonFakeDlopenOwnSo.setOnClickListener {
            val soPath = makeSureSoExists()
            // Native 代码加载自带的库(使用 fake_dlsym), 预期结果: 加载失败
            LoadArt.fakeLoadOwnSo(soPath)
        }
    }

    private fun makeSureSoExists(path: String = "libop-jni.so"): String {
        val file = getFileStreamPath(path)
        if (file.exists() && file.length() > 0) {
            Log.i("HelloJni", "$path exists at " + file.absolutePath)
            return file.absolutePath
        }
        assets.open(path).use { input ->
            openFileOutput(path, Context.MODE_PRIVATE).use {output ->
                input.copyTo(output, 1024 * 8)
                Log.i("HelloJni", "have copied libop-jni.so")
            }
        }
        return file.absolutePath
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoadSoActivity::class.java)
            context.startActivity(intent)
        }
    }
}