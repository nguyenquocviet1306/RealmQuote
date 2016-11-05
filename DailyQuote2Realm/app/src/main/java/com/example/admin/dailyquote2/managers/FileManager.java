package com.example.admin.dailyquote2.managers;

import android.content.Context;
import android.graphics.Bitmap;

import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import java.io.File;
import java.util.Random;

/**
 * Created by Admin on 10/18/2016.
 */

public class FileManager {
    private Storage storage ;
    private final static String IMAGE_DTR = "images";
    private final static String IMAGE_FILE_FORMAT = "unplash_%s.png";
    private final static String QUOTE_DTR = "quote";

    private FileManager(Context context){
        storage = SimpleStorage.getInternalStorage(context);
    }

    public void createImage(Bitmap bitmap,String filename ){
        storage.createFile(IMAGE_DTR, filename, bitmap );
    }

    public File loadImageFile(String fileName){
        return storage.getFile(IMAGE_DTR, fileName);
    }

    public void createImage(Bitmap bitmap,int index){
        createImage(bitmap, String.format(IMAGE_FILE_FORMAT, index ));
    }
    public File loadImageFile(int index){
        return loadImageFile(String.format(IMAGE_FILE_FORMAT , index ));
    }
    public File RandomImage() {
        int index = new Random().nextInt(Preferences.getInstance().getImageCount());
        return loadImageFile(index);
    }

    private static FileManager instance;
    public static FileManager getInstance() {
        return instance;
    }
    public static void init(Context context) {
        instance = new FileManager(context);
    }
}
