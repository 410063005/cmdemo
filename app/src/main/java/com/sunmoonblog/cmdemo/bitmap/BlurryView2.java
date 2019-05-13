package com.sunmoonblog.cmdemo.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

public class BlurryView2 extends BlurryView {
    public BlurryView2(Context context) {
        super(context);
    }

    public BlurryView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BlurryView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void process() {
        blur(mSourceBitmap, mBlurredBitmap);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blur(Bitmap sourceBitmap, Bitmap blurredBitmap) {
        mBlurInputAllocation.copyFrom(sourceBitmap); //把  源 bitmap 拷贝到 内核内存中
        mScriptIntrinsicBlur.setInput(mBlurInputAllocation); //设置高斯计算的输入内存
        mScriptIntrinsicBlur.forEach(mBlurOutputAllocation); //进行计算，并将计算结果输出到输出内存中
        mBlurOutputAllocation.copyTo(blurredBitmap); //将输出内存的的 bitmap 拷贝给 blurredBitmap 即模糊后的 bitMap
    }
}
