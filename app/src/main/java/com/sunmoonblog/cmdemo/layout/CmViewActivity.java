package com.sunmoonblog.cmdemo.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sunmoonblog.cmdemo.R;

public class CmViewActivity extends AppCompatActivity {

    private static final String TAG = "CmViewActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, CmViewActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new CmView(this));
        setContentView(R.layout.activity_cm_view);

        findViewById(R.id.cm_view).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch() called with: v = [" + v + "], event = [" + event + "]");
                return false;
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent() called with: ev = [" + ev + "]");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent() called with: event = [" + event + "]");
        return super.onTouchEvent(event);
    }
}
