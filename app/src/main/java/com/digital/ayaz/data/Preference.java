package com.digital.ayaz.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



public class Preference {


    private final String FILENAME = "movie_preference";
    private static Preference instance = null;
    private static final String DASH_BOARD = "dashBoard";
    private SharedPreferences sharedPrefrence;

    private static final String FROM_LOGIN = "login";
    private static final String TEST_TYPE = "test_type";
    private static final String USER_NAME = "name";
    private static final String STUDENT_RECORD = "student_record";


    private final String COMEPLETED = "completed";
    private final String TOKEN = "token";
    private String SORT_ORDER = "sort";


    public String getToken() {
        return sharedPrefrence.getString(TOKEN, null);
    }

    public void setToken(String token) {
        sharedPrefrence.edit().putString(TOKEN, token).commit();
    }

    public boolean isCompleted() {
        return sharedPrefrence.getBoolean(COMEPLETED, false);
    }

    public void setCompleted(boolean isCompleted) {
        sharedPrefrence.edit().putBoolean(COMEPLETED, isCompleted).commit();
    }

    public boolean isDashBoardCalled() {
        return sharedPrefrence.getBoolean(DASH_BOARD, false);
    }

    public void setDashBoardCalled(boolean isVideoTutorial) {
        sharedPrefrence.edit().putBoolean(DASH_BOARD, isVideoTutorial).commit();
    }

    public static Preference getInstance(Context context) {
        if (instance == null) {
            instance = new Preference(context);
        }
        return instance;
    }

    private Preference(Context context) {
        sharedPrefrence = context.getApplicationContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
    }


    public String getUserName() {
        return sharedPrefrence.getString(USER_NAME, null);
    }

    public void setUserName(String name) {
        sharedPrefrence.edit().putString(USER_NAME, name).commit();
    }

    public int getRecordCount() {
        return sharedPrefrence.getInt(STUDENT_RECORD, 0);
    }

    public void setRecordCount(int count) {
        sharedPrefrence.edit().putInt(STUDENT_RECORD, count).commit();
    }

    public void addToRecordCount(int count) {
        sharedPrefrence.edit().putInt(STUDENT_RECORD, sharedPrefrence.getInt(STUDENT_RECORD, 0) + count).commit();
    }

    public void updateRemovedRecordCount(int count) {
        int no = sharedPrefrence.getInt(STUDENT_RECORD, 0);
        if (no <= 0 || count > no) {
            sharedPrefrence.edit().putInt(STUDENT_RECORD, 0).commit();
        } else {
            sharedPrefrence.edit().putInt(STUDENT_RECORD, no - count).commit();
        }
    }

    public void setFromLogin(boolean isLogin) {
        sharedPrefrence.edit().putBoolean(FROM_LOGIN, isLogin).commit();
    }

    public boolean isFromLogin() {
        return sharedPrefrence.getBoolean(FROM_LOGIN, false);
    }

    public void setTestTypeMsg(String msg) {
        sharedPrefrence.edit().putString(TEST_TYPE, msg).commit();
    }

    public String getTestTypeMsg() {
        return sharedPrefrence.getString(TEST_TYPE, "");
    }
/*

    @SuppressWarnings("ResourceType")
    public static
    @MovieDataLoader.MovieStatus
    int getMovieStatus(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(context.getString(R.string.pref_movie_status_key), MovieDataLoader.MOVIE_STATUS_UNKNOWN);
    }

    public static String[] loadFavoriteMovieIds(Context context) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> favoritMovieIdsSet = prefs.getStringSet(MovieDetailsActivityFragment.FAVORITE_MOVIE_IDS_SET_KEY, null);

        if (favoritMovieIdsSet != null) {
            String[] array = new String[favoritMovieIdsSet.size()];

            Iterator<String> movieIdsIter = favoritMovieIdsSet.iterator();

            int i = 0;
            while (movieIdsIter.hasNext()) {
                array[i] = movieIdsIter.next();
                i = i + 1;
            }
            return array;
        }

        return null;
    }
*/

    public void setSortOrder(String sortOrder) {
        sharedPrefrence.edit().putString(SORT_ORDER, sortOrder).commit();
    }

    public String getSortOrder() {
        return sharedPrefrence.getString(SORT_ORDER, "");
    }
}