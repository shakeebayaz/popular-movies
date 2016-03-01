package com.digital.ayaz.UI;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digital.ayaz.Model.Movie;
import com.digital.ayaz.R;
import com.digital.ayaz.Utils.Constants;
import com.digital.ayaz.Utils.Utils;
import com.digital.ayaz.databinding.FragmentMovieDetailBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends BaseFragment {
    private Movie mMovie = new Movie();
    private FragmentMovieDetailBinding moviedetailBinding;
    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public void setMovieData(Movie movie) {
        mMovie = movie;
String genre= "Genre:";
        try {
            JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(mContext, "genre"));
            JSONArray genres = obj.optJSONArray("genres");
            for (int id : mMovie.getGenreIds()) {
                for (int i = 0; i < genres.length(); i++) {
                    obj=genres.optJSONObject(i);
                    if (id == obj.optInt("id")) {
                        genre = genre + " " + obj.opt("name") + ",";
                        break;
                    }
                }
            }
            if(genre.length()>0)
            genre=genre.substring(0,genre.length()-1);
            Log.e("json data", obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        moviedetailBinding.setData(mMovie);
        moviedetailBinding.setGenre(genre);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mData = getArguments().getParcelable(Constants.BundleKeys.DATA);
            mMovie = getArguments().getParcelable(Constants.BundleKeys.DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        moviedetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        // movieListBinding.setData(mMovie);
        moviedetailBinding.setData(mMovie);
        ((DashboardActivity) getActivity()).mToolbar.setNavigationIcon(R.drawable.ic_up);
        ((DashboardActivity) getActivity()).mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Activity activity = getActivity();
                if (activity != null) {
                    activity.onBackPressed();
                }
            }
        });
        ;
        return moviedetailBinding.getRoot();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
