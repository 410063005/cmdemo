package com.sunmoonblog.cmdemo.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

// https://stackoverflow.com/questions/3373860/convert-a-bitmap-to-grayscale-in-android
public class GrayView extends BlurryView {

    public GrayView(Context context) {
        super(context);
    }

    public GrayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GrayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void process() {
        grey(mSourceBitmap, mBlurredBitmap);
//        GrayUtils.getGrayscaledBitmap(getContext(), mSourceBitmap, mBlurredBitmap);
    }

    private void grey(Bitmap sourceBitmap, Bitmap blurredBitmap) {
        Canvas c = new Canvas(blurredBitmap);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(sourceBitmap, 0, 0, paint);
    }


}
