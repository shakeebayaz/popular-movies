package com.digital.ayaz.NetworkLayer;

import com.digital.ayaz.Model.Movie;

import com.digital.ayaz.Model.ReviewModel;
import com.digital.ayaz.Model.TrailerModel;
import com.digital.ayaz.Utils.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by Shakeeb on 2/11/2016.
 */
public class NetworkHelper {

    private MovieResponseListener mMovieResponseListener;

    public interface MovieResponseListener {
        void onMovieResponseReceive(Movie.Response data);
    }

    public void getMovieList(MovieResponseListener movieResponseListener, String sort, int pageIndex) {
        mMovieResponseListener = movieResponseListener;
        NetworkManager.getInstance().getMovie(mMoviewResponseCallback, sort, pageIndex);
    }


    private Callback<Movie.Response> mMoviewResponseCallback = new Callback<Movie.Response>() {
        @Override
        public void onResponse(Call<Movie.Response> call, Response<Movie.Response> response) {
            DialogUtils.hideProgressDialog();
            if (response != null)
                mMovieResponseListener.onMovieResponseReceive(response.body());
        }

        @Override
        public void onFailure(Call<Movie.Response> call, Throwable t) {
            DialogUtils.hideProgressDialog();
        }

    };

    private ReviewResponseListener mReviewResponseListener;

    public interface ReviewResponseListener {
        void onReviewResponseReceive(ReviewModel data);
    }

    public void getReviewList(ReviewResponseListener movieResponseListener, long id) {
        mReviewResponseListener = movieResponseListener;
        NetworkManager.getInstance().getReview(mReviewResponseCallback, id);
    }


    private Callback<ReviewModel> mReviewResponseCallback = new Callback<ReviewModel>() {
        @Override
        public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
            DialogUtils.hideProgressDialog();
            if (response != null)
                mReviewResponseListener.onReviewResponseReceive(response.body());
        }

        @Override
        public void onFailure(Call<ReviewModel> call, Throwable t) {
            DialogUtils.hideProgressDialog();
        }

    };
    private TrailerResponseListener mTrailerResponseListener;

    public interface TrailerResponseListener {
        void onTrailerResponseReceive(TrailerModel data);
    }

    public void getTrailerList(TrailerResponseListener movieResponseListener, long id) {
        mTrailerResponseListener = movieResponseListener;
        NetworkManager.getInstance().getTrailer(mTrailerResponseCallback, id);
    }


    private Callback<TrailerModel> mTrailerResponseCallback = new Callback<TrailerModel>() {
        @Override
        public void onResponse(Call<TrailerModel> call, Response<TrailerModel> response) {
            DialogUtils.hideProgressDialog();
            if (response != null)
                mTrailerResponseListener.onTrailerResponseReceive(response.body());
        }

        @Override
        public void onFailure(Call<TrailerModel> call, Throwable t) {
            DialogUtils.hideProgressDialog();
        }

    };

}
