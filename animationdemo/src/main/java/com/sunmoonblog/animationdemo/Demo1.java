package com.sunmoonblog.animationdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;

public class Demo1 extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private int mScreenWidth;
    private InterpolatorProvider mProvider = new InterpolatorProviderImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScreenWidth = getResources().getDisplayMetrics().widthPixels;

        final View view1 = findViewById(R.id.view1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(v, 0.33f, 0, 0.25f, 1, null);
            }
        });

        final View view2 = findViewById(R.id.view2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(v, 0.33f, 0, 0.25f, 1, mProvider);
            }
        });

        final View view3 = findViewById(R.id.view3);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(v, 0.33f, 0, 0.33f, 1, mProvider);
            }
        });


        final View view4 = findViewById(R.id.view4);
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(v, 0.33f, 0, 0.46f, 1, mProvider);
            }
        });

        final View view5 = findViewById(R.id.view5);
        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(v, 0.55f, 0, 0.1f, 1, mProvider);
            }
        });


        final View view6 = findViewById(R.id.view6);
        view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTranslationX(0);
                startSpringAnimation(v, SpringForce.DAMPING_RATIO_HIGH_BOUNCY, SpringForce.STIFFNESS_VERY_LOW);
            }
        });

        final View view7 = findViewById(R.id.view7);
        view7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTranslationX(0);
                startSpringAnimation(v, SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY, SpringForce.STIFFNESS_MEDIUM);
            }
        });
    }

    private void startSpringAnimation(View target, float dampingRatio, float stiffness) {
        final float tx = mScreenWidth / 2f - dpToPx(8) - target.getWidth();
        SpringAnimation animation = new SpringAnimation(target, SpringAnimation.TRANSLATION_X);
        SpringForce force = new SpringForce();
        force.setFinalPosition(tx);
        force.setDampingRatio(dampingRatio);
        force.setStiffness(stiffness);
        animation.setSpring(force);
        animation.start();
    }

    private void startAnimation(View target, float x1, float y1, float x2, float y2, InterpolatorProvider provider) {
        final float tx = mScreenWidth - target.getWidth() - dpToPx(8 + 8);
        ObjectAnimator oa = ObjectAnimator
                .ofFloat(target, "translationX", 0, tx, 0);
        oa.setDuration(500);
        if (provider != null) {
            oa.setInterpolator(provider.create(x1, y1, x2, y2));
        }
        oa.start();
    }


    private float dpToPx(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
