package com.digital.ayaz.UI;


import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.ayaz.Listener.MovieListClickedListener;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.Utils.Constants;
import com.digital.ayaz.adapter.MovieAdapter;
import com.digital.ayaz.NetworkLayer.NetworkHelper;
import com.digital.ayaz.R;
import com.digital.ayaz.Utils.DialogUtils;
import com.digital.ayaz.adapter.MovieListLoader;
import com.digital.ayaz.data.MoviesContract;
import com.digital.ayaz.data.Preference;
import com.digital.ayaz.databinding.FragmentMovieListBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment implements NetworkHelper.MovieResponseListener, LoaderManager.LoaderCallbacks<List<Movie>> {
    private MovieAdapter mMovieAdapter;
    List<Movie> mMovieList;
    private RecyclerView mRecyclerView;
    private int mPageIndex = 1;
    private String mSort=Constants.Sort.REVENUE_DESCENDING;
    private MovieListClickedListener mMovieListClickedListener;
    private GridLayoutManager mGridLayoutManager;
    private boolean isPagginating;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    public Movie.Response mMovie;
    private static final String MovieData = "movie_data";

    public void reloadData(List<Movie> list, MovieListClickedListener listener) {
        mMovieAdapter = new MovieAdapter(mContext, list, listener);
        mMovieList = list;
        mRecyclerView.setAdapter(mMovieAdapter);

    }

    public MovieListFragment() {

    }

    public void setMovieClickListener(MovieListClickedListener mMovieClickListener) {
        this.mMovieListClickedListener = mMovieClickListener;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMovieListBinding movieListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);

        mRecyclerView = movieListBinding.moviesRecyclerView;
        mGridLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mGridLayoutManager.getChildCount();
                    totalItemCount = mGridLayoutManager.getItemCount();
                    pastVisiblesItems = mGridLayoutManager.findFirstVisibleItemPosition();

                    if (!isPagginating) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {

                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            mPageIndex++;
                            isPagginating = true;
                            DialogUtils.displayProgressDialog(mContext);
                            reloadMovies(mSort);
                        }
                    }
                }
            }
        });
       // reloadMovies(String sort)
        DialogUtils.displayProgressDialog(mContext);
        if (TextUtils.isEmpty(Preference.getInstance(mContext).getSortOrder())) {
            reloadMovies(mSort);
        } else {
            mSort = Preference.getInstance(mContext).getSortOrder();
            reloadMovies(mSort);
        }
        return movieListBinding.getRoot();
    }

    public void reloadMovies(String sort) {
        mSort = sort;
        if (mSort != Constants.Sort.Favorite)
            new NetworkHelper().getMovieList(this, sort, mPageIndex);
        else
            getLoaderManager().initLoader(0, null, this).forceLoad();

    }


    public void scrollToTop() {
        if (mRecyclerView != null)
            mRecyclerView.scrollToPosition(0);
    }


    @Override
    public void onMovieResponseReceive(Movie.Response data) {
        if (data == null) {
            return;
        }
        mMovie = data;
        if(getResources().getBoolean(R.bool.isTablet))
        ((DashboardActivity)getActivity()).onMovieItemClicked(mMovie.getResults().get(0));
        if (mMovieAdapter != null && !isPagginating)
            mMovieAdapter.clear();
        if (!isPagginating)
            reloadData(data.getResults(), mMovieListClickedListener);
        else {
            mMovieList.addAll(data.getResults());
            mMovieAdapter.notifyDataSetChanged();
        }
        isPagginating = false;
    }


    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
        if (mMovieAdapter != null && !isPagginating)
            mMovieAdapter.clear();
        if (!isPagginating)
            reloadData(data, mMovieListClickedListener);
        else {
            mMovieList.addAll(data);
            mMovieAdapter.notifyDataSetChanged();
        }
        isPagginating = false;
        getLoaderManager().destroyLoader(0);
        DialogUtils.hideProgressDialog();
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {

    }
}



