package com.digital.ayaz.Utils;

/**
 * Created by techjini on 2/13/2016.
 */
public class Constants {

    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185";
    public static final String BACKDROP_BASE_URL = "http://image.tmdb.org/t/p/w500";

    public interface BundleKeys {
        String DATA = "com.digital.ayaz." + "movieData";
    }

    public interface Sort {
        String POPULARITY_ASCENDING = "popularity.asc";
        String POPULARITY_DESCENDING = "popularity.desc";
        String REVENUE_DESCENDING="revenue.desc";
        String VOTE_AVERAGE_ASCENDING = "vote_average.asc";
        String VOTE_AVERAGE_DESCENDING = "vote_average.desc";


    }
}
