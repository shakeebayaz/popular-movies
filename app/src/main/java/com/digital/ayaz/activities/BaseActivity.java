package com.digital.ayaz.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.digital.ayaz.R;

public class BaseActivity extends AppCompatActivity {

    public ProgressDialog mProgress;
    protected Context mContext;

    public void showProgress() {
        showProgress(getString(R.string.progress_msg));
    }

    public void showProgress(String msg) {
        if (mProgress == null) {
            mProgress = new ProgressDialog(this);
            mProgress.setCancelable(false);
            mProgress.getWindow().setGravity(Gravity.CENTER);
            mProgress.setMessage(msg);
            mProgress.setIndeterminate(true);
        }

        if (!mProgress.isShowing()) {
            mProgress.show();
        }
    }

    public void hideProgress() {
        if (getMainLooper().getThread().equals(Thread.currentThread())) {

            hideProgressInternal();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hideProgressInternal();
                }
            });
        }

    }

    protected void showSnack(Context context, int stringID) {

        Snackbar.make(((Activity) context).findViewById(android.R.id.content), stringID, Snackbar.LENGTH_LONG)
                .show();
    }

    private void hideProgressInternal() {
        if (mProgress != null && mProgress.isShowing() && !isFinishing()) {
            mProgress.dismiss();
        }
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showToast(final String msg) {

        if (getMainLooper().getThread().equals(Thread.currentThread())) {

            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        hideProgress();
        super.onDestroy();
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    protected void setToolBar(Toolbar toolBar, String title) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
    }

}
