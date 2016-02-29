package com.digital.ayaz.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.digital.ayaz.BuildConfig;
import com.digital.ayaz.Listener.MovieListClickedListener;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.NetworkLayer.NetworkHelper;
import com.digital.ayaz.R;


public class DashboardActivity extends AppCompatActivity implements NetworkHelper.MovieResponseListener,MovieListClickedListener {
    private boolean isTablet;
    private MovieListFragment mMovieListFragment;
    private MovieDetailFragment mMovieDetailFragment;
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
//        setActionBar();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Popular Movie");
        new NetworkHelper().getMovieList(this);
        isTablet = getResources().getBoolean(R.bool.isTablet);
        if (isTablet) {
            mMovieDetailFragment=new MovieDetailFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.movie_details_container, mMovieDetailFragment).commit();
        }
        mMovieListFragment= new MovieListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.movies_container,mMovieListFragment).commit();


    }

    @Override
    public void onMovieResponseReceive(Movie.Response data) {
        System.out.println();
        mMovieListFragment.reloadData(data.getResults(),this);

    }


    @Override
    public void onMovieItemClicked(Movie movie) {
        mMovieDetailFragment.setMovieData(movie);
    }
}
