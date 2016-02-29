package com.digital.ayaz.Utils;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Shakeeb on 2/13/2016.
 */
public class Utils {

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView view, String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            Glide.with(view.getContext()).load(Constants.baseURL + imagePath).placeholder(new ColorDrawable(Color.TRANSPARENT)).crossFade().into(view);
        }

    }
}
