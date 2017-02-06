package com.digital.ayaz.UI;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    protected Context mContext;

    public ProgressDialog mProgress;
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
}
