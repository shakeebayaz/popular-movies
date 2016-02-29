package com.digital.ayaz.UI;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.R;
import com.digital.ayaz.databinding.FragmentMovieDetailBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends BaseFragment {
    private Movie mMovie=new Movie();
    FragmentMovieDetailBinding moviedetailBinding;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public void setMovieData(Movie movie) {
        mMovie=movie;
        moviedetailBinding.setData(mMovie);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        moviedetailBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
       // movieListBinding.setData(mMovie);
        return moviedetailBinding.getRoot();

    }

}
