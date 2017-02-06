package com.digital.ayaz;

import android.app.Application;
import android.text.TextUtils;

import com.digital.ayaz.receiver_n_service.ConnectivityReceiver;

/**
 * Created by ats on 06/02/17.
 */
public class MyApplication extends Application{
    private static MyApplication mInstance;

    public MyApplication() {
        super();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override

    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
