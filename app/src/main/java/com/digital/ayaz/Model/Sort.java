package com.digital.ayaz.Model;

/**
 * Created by Shakeeb on 13/2/16.
 */
public enum Sort {

    POPULARITY_ASCENDING("popularity.asc"),
    POPULARITY_DESCENDING("popularity.desc"),
    VOTE_AVERAGE_ASCENDING("vote_average.asc"),
    VOTE_AVERAGE_DESCENDING("vote_average.desc");

    private final String value;

    Sort(String value) {
        this.value = value;
    }
}
