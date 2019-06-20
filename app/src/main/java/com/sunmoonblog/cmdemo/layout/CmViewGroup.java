package com.sunmoonblog.cmdemo.layout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

public class CmViewGroup extends FrameLayout {
    private static final String TAG = "CmViewGroup";

    private int mTouchSlop;

    boolean mIsScrolling;

    public CmViewGroup(Context context) {
        this(context, null);
    }

    public CmViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CmViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CmViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
        Log.i(TAG, "init(): mTouchSlop " + mTouchSlop);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent() called with: ev = [" + ev + "]");
        // return super.onInterceptTouchEvent(ev);


        int action = ev.getActionMasked();

        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mIsScrolling = false;
            x = -1;
            return false;
        }

        switch (action) {
            case MotionEvent.ACTION_MOVE:
                if (mIsScrolling) {
                    return true;
                }
                // If the user has dragged her finger horizontally more than
                // the touch slop, start the scroll

                // left as an exercise for the reader
                final int xDiff = calculateDistanceX(ev);

                // Touch slop should be calculated using ViewConfiguration
                // constants.
                Log.i(TAG, "onInterceptTouchEvent() xDiff = [" + xDiff + "]");
                if (xDiff > mTouchSlop) {
                    // Start scrolling!
                    mIsScrolling = true;
                    Log.i(TAG, "onInterceptTouchEvent() : start scrolling. called with: ev = [" + ev + "]");
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent() called with: event = [" + event + "]");

        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            if (mIsScrolling) {
                return true;
            }
        } else if (event.getActionMasked() == MotionEvent.ACTION_UP || event.getActionMasked() == MotionEvent.ACTION_CANCEL) {
            Log.i(TAG, "onInterceptTouchEvent() : stop scrolling. called with: ev = [" + event + "]");
            mIsScrolling = false;
            x = -1;
            return false;
        }

        return super.onTouchEvent(event);
    }

    int calculateDistanceX(MotionEvent ev) {
        if (x == -1) {
            x = ev.getX();
            return 0;
        }
        float deltaX = ev.getX() - x;

        x = ev.getX();

        return (int) Math.abs(deltaX);
    }

    private float x = -1;
}
