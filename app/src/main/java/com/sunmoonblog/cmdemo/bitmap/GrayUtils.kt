package com.sunmoonblog.cmdemo.bitmap

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.renderscript.*

class GrayUtils {

    companion object {

        //    https://xjaphx.wordpress.com/2011/06/21/image-processing-grayscale-image-on-the-fly/
        @JvmStatic
        @JvmOverloads
        fun getGrayscaledBitmapFallback(src: Bitmap, redVal: Float = 0.299f, greenVal: Float = 0.587f, blueVal: Float = 0.114f, dest: Bitmap?): Bitmap {
            // create output bitmap
            val bmOut = dest ?: Bitmap.createBitmap(src.width, src.height, src.config)
            // pixel information
            var A: Int
            var R: Int
            var G: Int
            var B: Int
            var pixel: Int
            // get image size
            val width = src.width
            val height = src.height
            // scan through every single pixel
            for (x in 0 until width) {
                for (y in 0 until height) {
                    // get one pixel color
                    pixel = src.getPixel(x, y)
                    // retrieve color of all channels
                    A = Color.alpha(pixel)
                    R = Color.red(pixel)
                    G = Color.green(pixel)
                    B = Color.blue(pixel)
                    // take conversion up to one single value
                    B = (redVal * R + greenVal * G + blueVal * B).toInt()
                    G = B
                    R = G
                    // set new pixel color to output bitmap
                    bmOut.setPixel(x, y, Color.argb(A, R, G, B))
                }
            }
            // return final image
            return bmOut
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @JvmStatic
        fun getGrayscaledBitmap(context: Context, src: Bitmap, dest: Bitmap?): Bitmap {
//        https://gist.github.com/imminent/cf4ab750104aa286fa08
//        https://en.wikipedia.org/wiki/Grayscale
            val redVal = 0.299f
            val greenVal = 0.587f
            val blueVal = 0.114f
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
                return getGrayscaledBitmapFallback(src, redVal, greenVal, blueVal, dest)
            val render = RenderScript.create(context)
            val matrix = Matrix4f(floatArrayOf(-redVal, -redVal, -redVal, 1.0f, -greenVal, -greenVal, -greenVal, 1.0f, -blueVal, -blueVal, -blueVal, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f))
            val result = dest ?: src.copy(src.config, true)
            val input = Allocation.createFromBitmap(render, src, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
            val output = Allocation.createTyped(render, input.type)
            // Inverts and do grayscale to the image
            @Suppress("DEPRECATION")
            val inverter =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                        ScriptIntrinsicColorMatrix.create(render)
                    else
                        ScriptIntrinsicColorMatrix.create(render, Element.U8_4(render))
            inverter.setColorMatrix(matrix)
            inverter.forEach(input, output)
            output.copyTo(result)
            // src.recycle()
            render.destroy()
            return result
        }
    }
}
