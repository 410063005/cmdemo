package com.sunmoonblog.animationdemo;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    float centerX;
    float centerY;

    SpringAnimation mAnimationX;

    float currentRotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View line = findViewById(R.id.line);

        line.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mAnimationX = new SpringAnimation(line, SpringAnimation.ROTATION);

                mAnimationX.setSpring(new SpringForce(0).setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY));

                centerX = line.getWidth() / 2f;
                centerY = line.getHeight() / 2f;

                line.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        line.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mAnimationX.cancel();
                        updateCurrentRotation(event.getX(), event.getY(), line);
                        return true;

                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        mAnimationX.start();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        updateCurrentRotation(event.getX(), event.getY(), line);
                        line.setRotation(currentRotation);//line.getRotation() + angle);
                        return true;
                }

                return false;
            }
        });
    }

    private void updateCurrentRotation(float x, float y, View view) {
        currentRotation = view.getRotation() + (float) Math.toDegrees(Math.atan2(x - centerX, centerY - y));
    }
}
