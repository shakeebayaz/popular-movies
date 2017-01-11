package com.digital.ayaz.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.ayaz.BR;
import com.digital.ayaz.Listener.MovieListClickedListener;
import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.R;

import java.util.List;

/**
 * Created by Shakeeb on 2/13/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Movie> mMovieList;
    private  Context mContext;
    private MovieListClickedListener mItemMovieListClickedListener;



    public MovieAdapter(Context context, List<Movie> list, MovieListClickedListener itemclickedListener) {
        mLayoutInflater = LayoutInflater.from(context);
        mMovieList = list;
        mContext=context;
        mItemMovieListClickedListener=itemclickedListener;
    }
    public void clear(){
        //TODO
        //lear list without using method
        mMovieList.clear();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(mLayoutInflater,
                R.layout.item_movie, parent, false);

        return new MovieViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie mMovie=mMovieList.get(position);
        holder.bindItem(mMovie);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setData(List<Movie> mMovieList) {
        this.mMovieList=mMovieList;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        public MovieViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void OnPosterClicked(View view) {
            final Movie movie = mMovieList.get(getAdapterPosition());
            mItemMovieListClickedListener.onMovieItemClicked(movie);

        }
        public void bindItem(Object object) {
            binding.setVariable(BR.data,object);
            binding.setVariable(BR.clickHandler,this);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


}
