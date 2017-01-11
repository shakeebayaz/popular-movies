package com.digital.ayaz.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnectedOrConnecting();
    }

  /*  public static String getImageURL(String imagePath) {
        StringBuilder imageURL = new StringBuilder();

        imageURL.append(IMAGE_BASE_URL);
        imageURL.append(IMAGE_SIZE);
        imageURL.append(PATH_SEPARATOR);
        imageURL.append(imagePath);

        return imageURL.toString();
    }*/

    public static Integer getYearFromDate(String dateStr) {
        SimpleDateFormat dbDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            Date inputDate = dbDateFormat.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(inputDate);

            return cal.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * This function is taken during search over internet
     * for making my list of items to show with individual heights
     * for 2 list views with different heights. And then it is modified
     * according to the MeasureSpec
     *
     * Original Source:
     * http://stackoverflow.com/questions/17693578/android-how-to-display-2-listviews-in-one-activity-one-after-the-other
     *
     *
     * @param mListView
     */
    public static void setDynamicHeight(ListView mListView) {
        ListAdapter mListAdapter = mListView.getAdapter();
        if (mListAdapter == null) {
            // when adapter is null
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < mListAdapter.getCount(); i++) {
            View listItem = mListAdapter.getView(i, null, mListView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount()));
        mListView.setLayoutParams(params);
        mListView.requestLayout();
    }

    public static boolean isStringEmpty(String string) {
        return (string == null || string.equals("null") || string.equals(""));
    }

    public static boolean isAppInstalled(String uri, Context context) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public static String getVideoHTML(String videoId) {

        String html =
                "<iframe class=\"youtube-player\" "
                        + "style=\"border: 0; width: 100%; height: 95%;"
                        + "padding:0px; margin:0px\" "
                        + "id=\"ytplayer\" type=\"text/html\" "
                        + "src=\"http://www.youtube.com/embed/" + videoId
                        + "?fs=0\" frameborder=\"0\" " + "allowfullscreen autobuffer "
                        + "controls onclick=\"this.play()\">\n" + "</iframe>\n";

/**
 * <iframe id="ytplayer" type="text/html" width="640" height="360"
 * src="https://www.youtube.com/embed/WM5HccvYYQg" frameborder="0"
 * allowfullscreen>
 **/

        return html;
    }

    public static boolean canResolveIntent(Intent intent, Context context) {
        List<ResolveInfo> resolveInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }

    public static String argsArrayToString(String[] args) {
        StringBuilder argsBuilder = new StringBuilder();

        final int argsCount = args.length;
        for (int i = 0; i < argsCount; i++) {
            argsBuilder.append(args[i]);

            if (i < argsCount - 1) {
                argsBuilder.append(",");
            }
        }

        return argsBuilder.toString();
    }

    public static boolean isMovieIdFavorite(String [] favoriteMovieIds, String movieId) {
        boolean result = false;

        if (favoriteMovieIds == null || favoriteMovieIds.length == 0) return result;

        for (int i = 0; i < favoriteMovieIds.length; i++) {
            if (movieId.trim().equals(favoriteMovieIds[i].trim())){
                result = true;
                break;
            }
        }

        return result;
    }





    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
