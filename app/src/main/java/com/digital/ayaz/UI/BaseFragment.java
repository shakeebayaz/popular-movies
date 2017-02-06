package com.digital.ayaz.UI;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.digital.ayaz.MyApplication;
import com.digital.ayaz.receiver_n_service.ConnectivityReceiver;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements ConnectivityReceiver.ConnectivityReceiverListener{
    protected Context mContext;

    public ProgressDialog mProgress;
    private TextView noInternetMsg;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
    protected void showSnack(Context context, int stringID) {

        Snackbar.make(((Activity) context).findViewById(android.R.id.content), stringID, Snackbar.LENGTH_LONG)
                .show();
    }

    private void hideProgressInternal() {
        if (mProgress != null && mProgress.isShowing() && !getActivity().isFinishing()) {
            mProgress.dismiss();
        }
    }

    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    protected void setInternetConnectionListner(TextView noInternetMsg) {
        MyApplication.getInstance().setConnectivityListener(this);
        this.noInternetMsg = noInternetMsg;

    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (noInternetMsg != null) {
            if (isConnected) {
                noInternetMsg.setVisibility(View.GONE);
            } else {
                noInternetMsg.setVisibility(View.VISIBLE);
            }
        }
    }
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (noInternetMsg != null)
            if (!isNetworkConnected()) {
                noInternetMsg.setVisibility(View.VISIBLE);
            } else {
                noInternetMsg.setVisibility(View.GONE);
            }
    }
}
