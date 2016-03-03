package com.digital.ayaz.Utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Shakeeb on 2/13/2016.
 */
public class Utils {

    @BindingAdapter({"app:imagePosterUrl"})
    public static void loadPosterImage(ImageView view, String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            Glide.with(view.getContext()).load(Constants.POSTER_BASE_URL + imagePath).placeholder(new ColorDrawable(Color.TRANSPARENT)).crossFade().into(view);
        }

    }
    @BindingAdapter({"app:imageBackdropUrl"})
    public static void loadBackdropImage(ImageView view, String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            Glide.with(view.getContext()).load(Constants.BACKDROP_BASE_URL + imagePath).placeholder(new ColorDrawable(Color.TRANSPARENT)).crossFade().into(view);
        }

    }


    public static String loadJSONFromAsset(Context context,String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename+".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
