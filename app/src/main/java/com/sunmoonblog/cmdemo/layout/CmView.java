package com.sunmoonblog.cmdemo.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class CmView extends View {

    private static final String TAG = "CmView";

    // Creation
    public CmView(Context context) {
        super(context);
        Log.i(TAG, "CmView() called with: context = [" + context + "]");
    }

    public CmView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "CmView() called with: context = [" + context + "], attrs = [" + attrs + "]");
    }

    public CmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "CmView() called with: context = [" + context + "], attrs = [" + attrs + "], defStyleAttr = [" + defStyleAttr + "]");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CmView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.i(TAG, "CmView() called with: context = [" + context + "], attrs = [" + attrs + "], " +
                "defStyleAttr = [" + defStyleAttr + "], defStyleRes = [" + defStyleRes + "]");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i(TAG, "onFinishInflate() called");
    }

    // Layout
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure() called with: widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "onLayout() called with: changed = [" + changed + "], left = [" + left + "], top = [" + top + "], right = [" + right + "], bottom = [" + bottom + "]");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i(TAG, "onSizeChanged() called with: w = [" + w + "], h = [" + h + "], oldw = [" + oldw + "], oldh = [" + oldh + "]");
    }

    // Drawing
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG, "onDraw() called with: canvas = [" + canvas + "]");
    }

    // Event processing

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyDown() called with: keyCode = [" + keyCode + "], event = [" + event + "]");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyUp() called with: keyCode = [" + keyCode + "], event = [" + event + "]");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent() called with: event = [" + event + "]");
        return super.onTouchEvent(event);
    }

    // Focus
    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.i(TAG, "onFocusChanged() called with: gainFocus = [" + gainFocus + "], direction = [" + direction + "], previouslyFocusedRect = [" + previouslyFocusedRect + "]");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.i(TAG, "onWindowFocusChanged() called with: hasWindowFocus = [" + hasWindowFocus + "]");
    }

    // Attaching
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow() called");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow() called");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.i(TAG, "onWindowVisibilityChanged() called with: visibility = [" + visibility + "]");
    }
}
