package com.digital.ayaz.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.ayaz.Listener.TrailerListClickedListener;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.Model.ReviewModel;
import com.digital.ayaz.Model.TrailerModel;
import com.digital.ayaz.NetworkLayer.NetworkHelper;
import com.digital.ayaz.R;
import com.digital.ayaz.Utils.Constants;
import com.digital.ayaz.Utils.DialogUtils;
import com.digital.ayaz.Utils.Utils;
import com.digital.ayaz.adapter.ReviewsListAdapter;
import com.digital.ayaz.adapter.TrailersListAdapter;
import com.digital.ayaz.data.MoviesContract;
import com.digital.ayaz.data.MoviesDbHelper;
import com.digital.ayaz.data.MoviesProvider;
import com.digital.ayaz.databinding.FragmentMovieDetailBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends BaseFragment implements View.OnClickListener, NetworkHelper.ReviewResponseListener, NetworkHelper.TrailerResponseListener, TrailerListClickedListener {
    private Movie mMovie = new Movie();
    private FragmentMovieDetailBinding moviedetailBinding;
    private boolean isFav;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public void setMovieData(Movie movie) {
        mMovie = movie;
        String genre = "Genre:";
        try {
            JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(mContext, "genre"));
            JSONArray genres = obj.optJSONArray("genres");
            for (int id : mMovie.getGenreIds()) {
                for (int i = 0; i < genres.length(); i++) {
                    obj = genres.optJSONObject(i);
                    if (id == obj.optInt("id")) {
                        genre = genre + " " + obj.opt("name") + ",";
                        break;
                    }
                }
            }
            if (genre.length() > 0)
                genre = genre.substring(0, genre.length() - 1);
            Log.e("json data", obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        moviedetailBinding.setData(mMovie);
        moviedetailBinding.setGenre(genre);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mData = getArguments().getParcelable(Constants.BundleKeys.DATA);
            mMovie = getArguments().getParcelable(Constants.BundleKeys.DATA);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        moviedetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        // movieListBinding.setData(mMovie);
        if (!getResources().getBoolean(R.bool.isTablet)) {
            ((DashboardActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setMovieData(mMovie);
        }
        ((DashboardActivity) getActivity()).mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Activity activity = getActivity();
                if (activity != null) {
                    activity.onBackPressed();
                }
            }
        });
        moviedetailBinding.setClickHandler(this);
        try {
            Cursor cursor = mContext.getContentResolver().query(
                    MoviesContract.MovieEntry.CONTENT_URI,
                    null, MoviesProvider.sMovieIdSelection, new String[]{"" + mMovie.getId()},
                    null);
            if (cursor != null && cursor.getCount() != 0)
                isFav = true;
            else {
                isFav = false;
            }
        } catch (SQLException e) {
            isFav = false;
        } catch (Exception e) {
            isFav = false;
        }

        moviedetailBinding.setIsFav(isFav);
        if (!getResources().getBoolean(R.bool.isTablet))
            ((DashboardActivity) getActivity()).hideOptionMenu(false);
        DialogUtils.displayProgressDialog(mContext);
        if (mMovie != null && mMovie.getId() > 0)
            new NetworkHelper().getReviewList(this, mMovie.getId());
        return moviedetailBinding.getRoot();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        if (!isFav) {
            mContext.getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI, MoviesDbHelper.populateContentValue(mMovie));
            moviedetailBinding.setIsFav(true);
        } else {
            mContext.getContentResolver().delete(MoviesContract.MovieEntry.CONTENT_URI, null, new String[]{"" + mMovie.getId()});
            moviedetailBinding.setIsFav(false);
        }
    }

    @Override
    public void onReviewResponseReceive(ReviewModel data) {
        if (data != null && data.results != null && data.results.size() > 0) {
            moviedetailBinding.reviewRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            moviedetailBinding.reviewRecyclerView.setAdapter(new ReviewsListAdapter(mContext, data.results));

            DialogUtils.displayProgressDialog(mContext);
            new NetworkHelper().getTrailerList(this, mMovie.getId());
        }
    }

    @Override
    public void onTrailerResponseReceive(TrailerModel data) {
        if (data != null && data.results != null && data.results.size() > 0) {
            moviedetailBinding.trailerRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            moviedetailBinding.trailerRecyclerView.setAdapter(new TrailersListAdapter(mContext, data.results, this));
        }
    }

    @Override
    public void onTrailerItemClicked(TrailerModel.Trailer movie) {
        startActivity(new Intent(getActivity(), YoutubeFullScreenActivity.class).putExtra(YoutubeFullScreenActivity.VIDEO_ID, movie.key));
    }
}
