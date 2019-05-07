package com.sunmoonblog.cmdemo.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sunmoonblog.cmdemo.R;

public class Utils {
    private Utils() {}

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).placeholder(R.color.grey).into(view);
    }
}
