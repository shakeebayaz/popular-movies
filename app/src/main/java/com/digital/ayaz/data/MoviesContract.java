package com.digital.ayaz.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by Shakeeb on 7/10/2015.
 */
public class MoviesContract {

    public static final String CONTENT_AUTHORITY = "com.digital.ayaz";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIE = "movie";
    public static final String PATH_TRAILER = "trailer";
    public static final String PATH_REVIEW = "review";

    public static long normalizeDate(long startDate) {
        Time time = new Time();
        time.set(startDate);
        int jul_day = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(jul_day);
    }

    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;

        // Table name
        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_IS_ADULT = "is_adult";
        public static final String COLUMN_BACK_DROP_PATH = "backdrop_path";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "releaseDate";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_IS_VIDEO = "is_video";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static Uri buildMovieUri() {
            /**
             * "content://com.popularmovies.app/movie"
             */

            return CONTENT_URI;
        }

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class MovieTrailerEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TRAILER).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRAILER;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRAILER;

        // Table name
        public static final String TABLE_NAME = "trailers";

        // Foreign Key of Movie as movie_id
        public static final String COLUMN_MOVIE_ID = "movie_id";

        public static final String COLUMN_TRAILER_ID = "id";
        public static final String COLUMN_ISO_369_1 = "iso_639_1";
        public static final String COLUMN_KEY = "key";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SITE = "site";
        public static final String COLUMN_SIZE = "size";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_DATE = "date";

        public static Uri buildMovieTrailerUri(long movieId) {
            /**
             * "content://com.popularmovies.app/trailers/135397"
             *  movieId = 135397
             */
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(movieId)).build();
        }

        public static String getMovieIdFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }

    public static final class MovieReviewEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_REVIEW).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEW;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_REVIEW;

        // Table name
        public static final String TABLE_NAME = "reviews";

        // Foreign Key of Movie as movie_id
        public static final String COLUMN_MOVIE_ID = "movie_id";

        public static final String COLUMN_REVIEW_ID = "id";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_DATE = "date";

        public static Uri buildMovieReviewsUri(long movieId) {
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(movieId)).build();
        }

        public static String getMovieIdFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }
    }
}