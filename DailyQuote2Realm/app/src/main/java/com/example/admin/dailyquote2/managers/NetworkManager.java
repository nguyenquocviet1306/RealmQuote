package com.example.admin.dailyquote2.managers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Admin on 10/18/2016.
 */

public class NetworkManager {
    ConnectivityManager connectivityManager;
    private NetworkManager(Context context) {
        connectivityManager  =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }
    public  boolean isConnectedToInterner(){
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return  true;
        } else {
            return  false;
        }
    }

    private static NetworkManager instance;

    public static NetworkManager getInstance(){
        return instance;
    }
    public static void init(Context context) {
        instance = new NetworkManager(context);

    }

}
