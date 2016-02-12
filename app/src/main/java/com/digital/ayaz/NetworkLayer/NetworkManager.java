package com.digital.ayaz.NetworkLayer;

import android.util.Log;

import com.digital.ayaz.BuildConfig;
import com.digital.ayaz.Model.Movie;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

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

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient();
// add your other interceptors …
// add logging as last interceptor
        httpClient.interceptors().add(logging);  // <-- this is the important line!

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }
/*

    public void getNews(Callback<Map<String, Map<String, String>>> callback) {
        ApiService service = getApiService();
        Call<Map<String, Map<String, String>>> model = service.getNewsList();
        model.enqueue(callback);

    }

    public void getCatalog(Callback<CatalogModel> catalogModelCallback) {
        Log.d(TAG, "getCatalog: ");
        ApiService apiService = getApiService();
        Call<CatalogModel> catalogModel = apiService.getCatalog("123456","1234567");
        catalogModel.enqueue(catalogModelCallback);
    }
*/

    public void getMovie(Callback<Movie.Response> catalogModelCallback) {
        Log.d(TAG, "Get Movie: ");
        ApiService apiService = getApiService();
        Call<Movie.Response> catalogModel = apiService.getMovieList();
        catalogModel.enqueue(catalogModelCallback);
    }


    private ApiService getApiService() {

        if (apiService == null)
            apiService = getRetroFit().create(ApiService.class);

        return apiService;
    }

}
