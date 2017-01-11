package com.digital.ayaz.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.ayaz.BR;
import com.digital.ayaz.Listener.TrailerListClickedListener;
import com.digital.ayaz.Model.TrailerModel;
import com.digital.ayaz.R;

import java.util.List;


/**
 * Created by Shakeeb on 10/1/2017.
 */


public class TrailersListAdapter extends RecyclerView.Adapter<TrailersListAdapter.MovieViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<TrailerModel.Trailer> mMovieList;
    private Context mContext;
    private TrailerListClickedListener mTrailerListClickedListener;

    public TrailersListAdapter(Context context, List<TrailerModel.Trailer> list,TrailerListClickedListener listClickedListener) {
        mLayoutInflater = LayoutInflater.from(context);
        mMovieList = list;
        mContext = context;
        mTrailerListClickedListener=listClickedListener;
    }

    public void clear() {
        mMovieList.clear();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(mLayoutInflater,
                R.layout.list_trailers_row, parent, false);

        return new MovieViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        TrailerModel.Trailer mMovie = mMovieList.get(position);
        holder.bindItem(mMovie);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        public MovieViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void OnPosterClicked(View view) {
            final TrailerModel.Trailer movie = mMovieList.get(getAdapterPosition());
            mTrailerListClickedListener.onTrailerItemClicked(movie);
        }

        public void bindItem(Object object) {
            binding.setVariable(BR.data, object);
            binding.setVariable(BR.clickHandler, this);
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
