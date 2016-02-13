package com.digital.ayaz.UI;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.ayaz.R;
import com.digital.ayaz.databinding.FragmentMovieListBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {


    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMovieListBinding movieListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);
        // Inflate the layout for this fragment
        return movieListBinding.getRoot();
    }

}
