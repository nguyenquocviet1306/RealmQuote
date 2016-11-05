package com.example.admin.dailyquote2.services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;


import com.example.admin.dailyquote2.constants.Constants;
import com.example.admin.dailyquote2.managers.FileManager;
import com.example.admin.dailyquote2.managers.Preferences;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Admin on 10/23/2016.
 */

public class UnplashDowloadService extends IntentService {

    private static final String TAG = UnplashDowloadService.class.toString();
    private static final int IMAGE_COUNT = 10;

    public UnplashDowloadService() {

        super("UnplashDowloadService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
//        while (true) {
//            try {
//                Thread.sleep(500);
//                Log.d(TAG,"Blal");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        for (int  i = 0; i < IMAGE_COUNT; i++) {
            Log.d(TAG, String.format("Unplash #%s", i));
            Log.d(TAG,"Dowloading image");

            // 1 : Download image
            Bitmap bitmap = ImageLoader
                    .getInstance()
                    .loadImageSync(Constants.UNSPLASH_API);
            Log.d(TAG,"Done Downloading ");

            Log.d(TAG,"Saving downoaded image");
            // 2 : Save
            FileManager.getInstance().createImage(bitmap,i);
            Log.d(TAG,"Done saving");
            Preferences.getInstance().putImageCount(i + 1);
//            DbHelper.getInstance().insert(bitmap);

        }

    }


}
