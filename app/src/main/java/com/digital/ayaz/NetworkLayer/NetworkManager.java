package com.digital.ayaz.NetworkLayer;

import android.util.Log;

import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.Model.ReviewModel;
import com.digital.ayaz.Model.TrailerModel;
import com.digital.ayaz.Utils.Constants;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;


/**
 * Created by techjini on 5/11/15.
 */
public class NetworkManager {
    private static final String TAG = NetworkManager.class.getSimpleName();
    private static NetworkManager ourInstance;
    private ApiService apiService;

    public static NetworkManager getInstance() {
        if (ourInstance == null) {
            ourInstance = new NetworkManager();
        }
        return ourInstance;
    }


    private Retrofit getRetroFit() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);
        httpClient.readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);

        if (Constants.IS_DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);  // <-- this is the important line!
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit;

    }
    public void getMovie(Callback<Movie.Response> catalogModelCallback, String sort, int pageIndex) {
        Log.d(TAG, "Get Movie: ");
        ApiService apiService = getApiService();
        Call<Movie.Response> catalogModel = apiService.getMovieList(sort,pageIndex);
        catalogModel.enqueue(catalogModelCallback);
    }
    public void getReview(Callback<ReviewModel> catalogModelCallback, long id) {
        Log.d(TAG, "Get Movie: ");
        ApiService apiService = getApiService();
        Call<ReviewModel> catalogModel = apiService.getMovieReviews(id);
        catalogModel.enqueue(catalogModelCallback);
    }
    public void getTrailer(Callback<TrailerModel> catalogModelCallback, long id) {
        Log.d(TAG, "Get Movie: ");
        ApiService apiService = getApiService();
        Call<TrailerModel> catalogModel = apiService.getMovieTrailer(id);
        catalogModel.enqueue(catalogModelCallback);
    }


    private ApiService getApiService() {

        if (apiService == null)
            apiService = getRetroFit().create(ApiService.class);

        return apiService;
    }

}
