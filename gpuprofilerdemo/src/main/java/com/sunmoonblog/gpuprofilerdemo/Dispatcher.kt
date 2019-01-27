package com.sunmoonblog.gpuprofilerdemo

import android.content.Context

object Dispatcher {

    fun dispatch(context: Context, colorItem: ColorItem) {
        when (colorItem.id) {
            2 -> HeavyIssueActivity.start(context, colorItem)
            3 -> HeavyUploadActivity.start(context, colorItem)
            4 -> HeavyDrawActivity.start(context, colorItem)
//            5 -> HeavyLayoutActivity.start(context, colorItem)
            5 -> HeavyLayoutActivity2.start(context, colorItem)
            6 -> HeavyAnimationActivity.start(context, colorItem)
            7 -> HeavyInputActivity.start(context, colorItem)
            8 -> HeavyMiscActivity.start(context, colorItem)
        }
    }
}