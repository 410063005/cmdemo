package com.sunmoonblog.animationdemo;

import android.animation.TimeInterpolator;

public interface InterpolatorProvider {
    TimeInterpolator create(float x1, float y1, float x2, float y2);
}
