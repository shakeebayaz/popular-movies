package com.digital.ayaz.NetworkLayer;

import com.digital.ayaz.BuildConfig;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.Model.ReviewModel;
import com.digital.ayaz.Model.TrailerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Shakeeb on 1/11/2016
 */
public interface ApiService {


    @GET("discover/movie?api_key=" + BuildConfig.API_KEY)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<Movie.Response> getMovieList(@Query("sort_by") String sort,
                                      @Query("page") int page);

    @GET("movie/{id}/reviews?api_key=" + BuildConfig.API_KEY)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<ReviewModel> getMovieReviews(@Path("id") long id);

    @GET("movie/{id}/videos?api_key=" + BuildConfig.API_KEY)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<TrailerModel> getMovieTrailer(@Path("id") long id);

}
