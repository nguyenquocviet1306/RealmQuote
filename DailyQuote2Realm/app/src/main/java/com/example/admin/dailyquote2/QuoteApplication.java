package com.example.admin.dailyquote2;

import android.app.Application;
import android.util.Log;


import com.example.admin.dailyquote2.managers.DbContext;
import com.example.admin.dailyquote2.managers.FileManager;
import com.example.admin.dailyquote2.managers.Preferences;
import com.example.admin.dailyquote2.managers.NetworkManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Admin on 10/12/2016.
 */

public class QuoteApplication extends Application {
    private static final String TAG = QuoteApplication.class.toString();

    @Override
    public void onCreate() {

        super.onCreate();

        Preferences.init(this);
        NetworkManager.init(this);
        FileManager.init(this);
        DbContext.init(this);
        initImageLoader();
        if (NetworkManager.getInstance().isConnectedToInterner()) {
            Log.d(TAG, "Connected");
        } else {
            Log.d(TAG, "Not Connected");
        }
//        Quote quote =  new Quote("Hoang","Chu trang trai");
//        DbHelper.getInstance().insert(
//               quote
//        );
        Log.d(TAG,"Inserted");
//        for (Quote q : DbHelper.getInstance().selectAllQuote()){
//            Log.d(TAG,q.toString());
//        }
//        Log.d(TAG,DbContext.getInstance().selecRandomQuote().toString());


    }
    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
