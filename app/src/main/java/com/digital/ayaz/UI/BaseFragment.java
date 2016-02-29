package com.digital.ayaz.UI;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    protected Context mContext;


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
}
