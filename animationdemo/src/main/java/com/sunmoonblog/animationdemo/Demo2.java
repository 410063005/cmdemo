package com.sunmoonblog.animationdemo;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class Demo2 extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    float dx;
    float dy;

    SpringAnimation mAnimationX;
    SpringAnimation mAnimationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        final ImageView imageView = findViewById(R.id.imageView);


        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {


                mAnimationX = new SpringAnimation(imageView, SpringAnimation.X);
                mAnimationY = new SpringAnimation(imageView, SpringAnimation.Y);

                mAnimationX.setSpring(new SpringForce(imageView.getX()).setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY));
                mAnimationY.setSpring(new SpringForce(imageView.getY()).setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY));

                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dx = v.getX() - event.getRawX();
                        dy = v.getY() - event.getRawY();

                        mAnimationX.cancel();
                        mAnimationY.cancel();
                        return true;

                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        mAnimationX.start();
                        mAnimationY.start();
                        return true;

                    case MotionEvent.ACTION_MOVE:
//                        imageView.animate()
//                                .x(event.getRawX() + dx)
//                                .y(event.getRawY() + dy)
//                                .setDuration(0)
//                                .start();
                        imageView.setX(event.getRawX() + dx);
                        imageView.setY(event.getRawY() + dy);
                        return true;
                }

                return false;
            }
        });
    }
}
