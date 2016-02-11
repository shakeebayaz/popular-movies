package com.digital.ayaz.NetworkLayer;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/discover/movie?").addConverterFactory(GsonConverterFactory.create())
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


    private ApiService getApiService() {

        if (apiService == null)
            apiService = getRetroFit().create(ApiService.class);

        return apiService;
    }

}
