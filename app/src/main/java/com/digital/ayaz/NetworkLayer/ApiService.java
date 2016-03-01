package com.digital.ayaz.NetworkLayer;

import com.digital.ayaz.BuildConfig;
import com.digital.ayaz.Model.Movie;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by Shakeeb on 1/11/2016
 */
public interface ApiService {


    @GET("discover/movie?api_key="+ BuildConfig.API_KEY)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<Movie.Response> getMovieList(@Query("sort_by") String sort,
                                      @Query("page") int page);

}
