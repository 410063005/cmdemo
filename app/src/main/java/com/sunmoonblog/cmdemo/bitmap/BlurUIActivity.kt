package com.sunmoonblog.cmdemo.bitmap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.sunmoonblog.cmdemo.R
import kotlinx.android.synthetic.main.activity_blur_ui.*


class BlurUIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur_ui)

        val adapter = BlurUIAdapter(DIFF_CALLBACK)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        adapter.submitList(listOf(
                BlurUIItem("10000000", "http://a.hiphotos.baidu.com/image/pic/item/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg"),
                BlurUIItem("fdsfasdfasdfasdf1", "http://b.hiphotos.baidu.com/image/pic/item/908fa0ec08fa513db777cf78376d55fbb3fbd9b3.jpg"),
                BlurUIItem("1fdsfsadfasdf", "http://f.hiphotos.baidu.com/image/pic/item/0e2442a7d933c8956c0e8eeadb1373f08202002a.jpg"),
                BlurUIItem("10000", "http://f.hiphotos.baidu.com/image/pic/item/b151f8198618367aa7f3cc7424738bd4b31ce525.jpg"),
                BlurUIItem("1dsfasdfasdfsadf", "http://a.hiphotos.baidu.com/image/pic/item/f603918fa0ec08fa3139e00153ee3d6d55fbda5f.jpg"),
                BlurUIItem("0fddddddddddd1", "http://h.hiphotos.baidu.com/image/pic/item/b3b7d0a20cf431ad7427dfad4136acaf2fdd98a9.jpg"),
                BlurUIItem("1", "http://f.hiphotos.baidu.com/image/pic/item/d058ccbf6c81800aec9d109abb3533fa828b472e.jpg"),
                BlurUIItem("1", "http://a.hiphotos.baidu.com/image/pic/item/0b46f21fbe096b636660c7c406338744ebf8ac2d.jpg"),
                BlurUIItem("1", "http://c.hiphotos.baidu.com/image/pic/item/c8177f3e6709c93d7afd9163953df8dcd1005412.jpg"),
                BlurUIItem("1", "http://a.hiphotos.baidu.com/image/pic/item/2934349b033b5bb569adb96a3cd3d539b600bc28.jpg"),
                BlurUIItem("1", "http://f.hiphotos.baidu.com/image/pic/item/5fdf8db1cb134954e4eb74f05c4e9258d0094aa9.jpg"),
                BlurUIItem("1", "http://e.hiphotos.baidu.com/image/pic/item/7a899e510fb30f2476207097c295d143ac4b03ef.jpg"),
                BlurUIItem("1", "http://d.hiphotos.baidu.com/image/pic/item/7af40ad162d9f2d324bab87ba3ec8a136227ccff.jpg"),
                BlurUIItem("1", "http://e.hiphotos.baidu.com/image/pic/item/b8389b504fc2d562510a4d79ed1190ef76c66c20.jpg"),
                BlurUIItem("1", "http://c.hiphotos.baidu.com/image/pic/item/ae51f3deb48f8c54768e57bb30292df5e1fe7fc1.jpg"),
                BlurUIItem("1", "http://e.hiphotos.baidu.com/image/pic/item/314e251f95cad1c8b33052b3753e6709c83d519e.jpg"),
                BlurUIItem("1", "http://g.hiphotos.baidu.com/image/pic/item/aa18972bd40735fa789b4dfe90510fb30f240809.jpg"),
                BlurUIItem("1", "http://b.hiphotos.baidu.com/image/pic/item/d833c895d143ad4bd06aae8f8c025aafa40f063c.jpg"),
                BlurUIItem("1", "http://d.hiphotos.baidu.com/image/pic/item/7a899e510fb30f24e1f1ef44c695d143ad4b0309.jpg"),
                BlurUIItem("1", "http://g.hiphotos.baidu.com/image/pic/item/4d086e061d950a7b3492a68b04d162d9f3d3c9de.jpg"),
                BlurUIItem("1", "http://a.hiphotos.baidu.com/image/pic/item/838ba61ea8d3fd1fc9c7b6853a4e251f94ca5f46.jpg"),
                BlurUIItem("1", "http://b.hiphotos.baidu.com/image/pic/item/d833c895d143ad4bd06aae8f8c025aafa40f063c.jpg")
        ))
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, BlurUIActivity::class.java)
            context.startActivity(intent)
        }
    }
}