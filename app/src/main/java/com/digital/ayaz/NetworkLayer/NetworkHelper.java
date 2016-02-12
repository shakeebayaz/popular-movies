package com.digital.ayaz.NetworkLayer;

import android.util.Log;

import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.Utils.DialogUtils;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Shakeeb on 2/11/2016.
 */
public class NetworkHelper {

    private MovieResponseListener mMovieResponseListener;

    public interface MovieResponseListener {
        void onMovieResponseReceive(Movie.Response data);
    }
    public void getMovieList(MovieResponseListener movieResponseListener){
        mMovieResponseListener=movieResponseListener;
        NetworkManager.getInstance().getMovie(mMoviewResponseCallback);
    }


    private Callback<Movie.Response> mMoviewResponseCallback = new Callback<Movie.Response>() {
        @Override
        public void onResponse(Response<Movie.Response> response, Retrofit retrofit) {
            DialogUtils.hideProgressDialog();
            if(response!=null)
            mMovieResponseListener.onMovieResponseReceive(response.body());
        }

        @Override
        public void onFailure(Throwable t) {
           // Log.e(TAG, "onFailure: ");
            DialogUtils.hideProgressDialog();
        }
    };


}
