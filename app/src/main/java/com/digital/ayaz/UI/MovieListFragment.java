package com.digital.ayaz.UI;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.ayaz.Listener.MovieListClickedListener;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.MovieAdapter;
import com.digital.ayaz.R;
import com.digital.ayaz.Utils.Constants;
import com.digital.ayaz.databinding.FragmentMovieListBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment {
private MovieAdapter mMovieAdapter;
    List<Movie> mMovieList;
    private RecyclerView recyclerView;
    public MovieListFragment() {
        // Required empty public constructor
    }
    public void reloadData(List<Movie>list,MovieListClickedListener listener){
        mMovieAdapter=new MovieAdapter(mContext,list,listener);
        mMovieList=list;
        recyclerView.setAdapter(mMovieAdapter);

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMovieListBinding movieListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);
        recyclerView  = movieListBinding.moviesRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));


        return movieListBinding.getRoot();
    }


}
