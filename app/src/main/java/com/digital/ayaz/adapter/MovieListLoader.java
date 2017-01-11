package com.digital.ayaz.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.data.MoviesContract;

import java.util.ArrayList;
import java.util.List;


/**
 * Movies async task loader.
 * Created by abhelly on 23.06.15.
 */
public class MovieListLoader extends AsyncTaskLoader<List<Movie>> {
    private List<Long> mFavoriteIds;

    public MovieListLoader(Context context) {
        super(context);
    }

    public static final String[] MOVIE_COLUMNS = {
            MoviesContract.MovieEntry.TABLE_NAME + "." + MoviesContract.MovieEntry._ID,
            MoviesContract.MovieEntry.COLUMN_MOVIE_ID,
            MoviesContract.MovieEntry.COLUMN_IS_ADULT,
            MoviesContract.MovieEntry.COLUMN_BACK_DROP_PATH,
            MoviesContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE,
            MoviesContract.MovieEntry.COLUMN_ORIGINAL_TITLE,
            MoviesContract.MovieEntry.COLUMN_OVERVIEW,
            MoviesContract.MovieEntry.COLUMN_RELEASE_DATE,
            MoviesContract.MovieEntry.COLUMN_POSTER_PATH,
            MoviesContract.MovieEntry.COLUMN_POPULARITY,
            MoviesContract.MovieEntry.COLUMN_TITLE,
            MoviesContract.MovieEntry.COLUMN_IS_VIDEO,
            MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE,
            MoviesContract.MovieEntry.COLUMN_VOTE_COUNT,
    };

    int id_index = 0;
    int COLUMN_MOVIE_ID = 1;
    int COLUMN_IS_ADULT = 2;
    int COLUMN_BACK_DROP_PATH = 3;
    int COLUMN_ORIGINAL_LANGUAGE = 4;
    int COLUMN_ORIGINAL_TITLE = 5;
    int COLUMN_OVERVIEW = 6;
    int COLUMN_RELEASE_DATE = 7;
    int COLUMN_POSTER_PATH = 8;
    int COLUMN_POPULARITY = 9;
    int COLUMN_TITLE = 10;
    int COLUMN_IS_VIDEO = 11;
    int COLUMN_VOTE_AVERAGE = 12;
    int COLUMN_VOTE_COUNT = 13;



    @Override

    protected void onStartLoading() {
        super.onStartLoading();

    }

    @Override
    public List<Movie> loadInBackground() {
        Cursor cursor = getContext().getContentResolver().query(MoviesContract.MovieEntry.buildMovieUri(), MOVIE_COLUMNS, null, null, null);
        List<Movie> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Movie model = new Movie();
            model.setId(cursor.getLong(COLUMN_MOVIE_ID));
            model.setTitle(cursor.getString(COLUMN_TITLE));
            model.setPosterPath(cursor.getString(COLUMN_POSTER_PATH));
            model.setOverview(cursor.getString(COLUMN_OVERVIEW));
            model.setReleaseDate(cursor.getString(COLUMN_RELEASE_DATE));
            try {
                model.setVoteAverage(Double.parseDouble(cursor.getString(COLUMN_VOTE_AVERAGE)));
            }catch (Exception e){

            }
            list.add(model);
        }
        return list;
    }



    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
