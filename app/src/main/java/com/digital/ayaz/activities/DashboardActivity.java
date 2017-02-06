package com.digital.ayaz.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.digital.ayaz.Listener.MovieListClickedListener;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.R;
import com.digital.ayaz.UI.MovieDetailFragment;
import com.digital.ayaz.UI.MovieListFragment;
import com.digital.ayaz.Utils.Constants;
import com.digital.ayaz.Utils.DialogUtils;
import com.digital.ayaz.Utils.Utils;
import com.digital.ayaz.data.Preference;


public class DashboardActivity extends BaseActivity implements MovieListClickedListener {
    private boolean isTablet;
    private MovieListFragment mMovieListFragment;
    private MovieDetailFragment mMovieDetailFragment;
    private String mSort = Constants.Sort.REVENUE_DESCENDING;
    public Toolbar mToolbar;
    private Menu mMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);

            isTablet = getResources().getBoolean(R.bool.isTablet);

            if (isTablet) {
                mMovieDetailFragment = new MovieDetailFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.movie_details_container, mMovieDetailFragment).commit();
            }
            mMovieListFragment = new MovieListFragment();
            mMovieListFragment.setMovieClickListener(this);
            getSupportFragmentManager().beginTransaction().add(R.id.movies_container, mMovieListFragment).commit();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if (!isFinishing())
            hideOptionMenu(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_popularity:
                item.setChecked(!item.isChecked());
                Preference.getInstance(this).setSortOrder(Constants.Sort.POPULARITY_DESCENDING);
                onSortChanged(Constants.Sort.POPULARITY_DESCENDING);

                break;
            case R.id.menu_sort_rating:
                item.setChecked(!item.isChecked());
                DialogUtils.displayProgressDialog(this);
                Preference.getInstance(this).setSortOrder(Constants.Sort.VOTE_AVERAGE_DESCENDING);
                onSortChanged(Constants.Sort.VOTE_AVERAGE_DESCENDING);

                break;
            case R.id.menu_sort_Favorite:
                item.setChecked(!item.isChecked());
                DialogUtils.displayProgressDialog(this);
                Preference.getInstance(this).setSortOrder(Constants.Sort.Favorite);
                onSortChanged(Constants.Sort.Favorite);

                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void onSortChanged(String sort) {
        if (Utils.isConnectionAvailable(this)) {
            DialogUtils.displayProgressDialog(this);
            mSort = sort;
            mMovieListFragment.reloadMovies(sort);
            mMovieListFragment.scrollToTop();
        } else {
            DialogUtils.showToast(getResources().getString(R.string.no_network), this);
        }
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
            getSupportFragmentManager().beginTransaction().add(R.id.movies_container, mMovieDetailFragment).addToBackStack("detail_fragment").commit();
        }
    }

    public void hideOptionMenu(boolean bol) {
        if(mToolbar!=null && mToolbar.getMenu().findItem(R.id.menu_sort)!=null)
        mToolbar.getMenu().findItem(R.id.menu_sort).setVisible(bol);
    }

}
