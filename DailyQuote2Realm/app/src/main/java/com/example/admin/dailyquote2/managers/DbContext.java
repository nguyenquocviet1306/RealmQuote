package com.example.admin.dailyquote2.managers;

import android.content.ContentValues;
import android.content.Context;

import com.example.admin.dailyquote2.models.Quote;

import java.util.List;
import java.util.Random;

import io.realm.Realm;

/**
 * Created by Admin on 11/5/2016.
 */

public class DbContext {
    private Realm realm;
    private static DbContext instance;
    public static DbContext getInstance(){
        return instance;
    }

    public static void init(Context context) {
        instance = new DbContext(context);
    }

    private DbContext(Context context){
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }
    public void addQuote(Quote quote) {
        realm.beginTransaction();
        realm.copyToRealm(quote);
        realm.commitTransaction();
    }

    public Quote getRandom(){
        List<Quote> quoteList = realm.where(Quote.class).findAll();
        Random r = new Random();
        return quoteList.get(r.nextInt(quoteList.size()));
    }
}
