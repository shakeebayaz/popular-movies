package com.digital.ayaz.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.digital.ayaz.Listener.MovieListClickedListener;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.R;
import com.digital.ayaz.Utils.Constants;
import com.digital.ayaz.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;


public class DashboardActivity extends AppCompatActivity implements MovieListClickedListener {
    private boolean isTablet;
    private MovieListFragment mMovieListFragment;
    private MovieDetailFragment mMovieDetailFragment;
    private String mSort = Constants.Sort.REVENUE_DESCENDING;
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
//        setActionBar();

        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        isTablet = getResources().getBoolean(R.bool.isTablet);

        if (isTablet) {
            mMovieDetailFragment = new MovieDetailFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.movie_details_container, mMovieDetailFragment).commit();
        }
        mMovieListFragment = new MovieListFragment();
        mMovieListFragment.setMovieClickListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.movies_container, mMovieListFragment).commit();

        onSortChanged(mSort);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_popularity:
                item.setChecked(!item.isChecked());
                onSortChanged(Constants.Sort.POPULARITY_DESCENDING);
                break;
            case R.id.menu_sort_rating:
                item.setChecked(!item.isChecked());
                onSortChanged(Constants.Sort.VOTE_AVERAGE_DESCENDING);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onSortChanged(String sort) {
        mSort = sort;
        mMovieListFragment.reloadMovies(sort);
        mMovieListFragment.scrollToTop();
    }

    @Override
    public void onMovieItemClicked(Movie movie) {
        if (isTablet)
            mMovieDetailFragment.setMovieData(movie);
        else {
            mMovieDetailFragment = new MovieDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.BundleKeys.DATA, movie);
            mMovieDetailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.movies_container, mMovieDetailFragment).addToBackStack(null).commit();
        }
    }
}
