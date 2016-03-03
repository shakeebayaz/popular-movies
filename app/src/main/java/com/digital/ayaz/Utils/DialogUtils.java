package com.digital.ayaz.Utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.digital.ayaz.R;


/**
 * Created by Shakeeb on 12/2/16.
 */
public class DialogUtils {

    private static ProgressDialog sProgressDialog;

    public static void displayProgressDialog(Context context) {
        displayProgressDialog(context, null);

    }
    /**
     * Creates and shows progress dialog and sets your OnCancelListener
     *
     * @param context
     * @param listener
     */
    public static void displayProgressDialog(Context context,
                                             OnCancelListener listener) {
        displayProgressDialog(context, listener,
                context.getString(R.string.loading));

    }

    /**
     * Creates and shows progress dialog with given message and sets your
     * OnCancelListener
     *
     * @param context
     * @param listener
     * @param msg
     */
    public static void displayProgressDialog(Context context,
                                             OnCancelListener listener, String msg) {
        if (sProgressDialog != null && sProgressDialog.isShowing())
            return;

        if (context != null) {
            sProgressDialog = new ProgressDialog(context);
            sProgressDialog.setMessage(msg);
//            sProgressDialog.setTitle(R.string.app_name);
            sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            sProgressDialog.setCancelable(false);
            sProgressDialog.setOnCancelListener(listener);
            try {
                sProgressDialog.show();
            } catch (WindowManager.BadTokenException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Dismisses progress dialog.
     */
    public static void hideProgressDialog() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            try {
                sProgressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            sProgressDialog = null;
        }
    }
    public static void showToast(String msg, Context ctx) {
        //TODO Add flag to enable or disable toast
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }


}
