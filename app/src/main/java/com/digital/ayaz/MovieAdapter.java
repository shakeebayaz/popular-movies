package com.digital.ayaz;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.digital.ayaz.Model.Movie;

import java.util.List;

/**
 * Created by Shakeeb on 2/13/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    LayoutInflater mLayoutInflater;
    List<Movie> mMovieList;


    public MovieAdapter(Context context, List<Movie> list) {
        mLayoutInflater = LayoutInflater.from(context);
        mMovieList = list;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(mLayoutInflater,
                R.layout.item_movie, parent, false);

        return new MovieViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public MovieViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            binding.setVariable(BR.data, mMovieList);
        }
    }
}
