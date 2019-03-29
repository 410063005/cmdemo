package com.sunmoonblog.animationdemo;

import android.animation.TimeInterpolator;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

class InterpolatorProviderImpl implements InterpolatorProvider {
    @Override
    public TimeInterpolator create(float x1, float y1, float x2, float y2) {
        return createInterpolator(x1, y1, x2, y2);
    }

    private Interpolator createInterpolator(float x1, float y1, float x2, float y2) {
        return PathInterpolatorCompat.create(x1, y1, x2, y2);
    }
}
