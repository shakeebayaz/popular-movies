package com.digital.ayaz.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.digital.ayaz.BuildConfig;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.NetworkLayer.NetworkHelper;
import com.digital.ayaz.R;


public class DashboardActivity extends AppCompatActivity implements NetworkHelper.MovieResponseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        new NetworkHelper().getMovieList(this);
    }

    @Override
    public void onMovieResponseReceive(Movie.Response data) {
        System.out.println();
    }
}
