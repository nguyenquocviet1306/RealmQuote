package com.example.admin.dailyquote2.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.admin.dailyquote2.managers.DbContext;
import com.example.admin.dailyquote2.models.Quote;
import com.example.admin.dailyquote2.network.QuoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Admin on 11/5/2016.
 */

public class QuoteDowloadByRetrofit extends IntentService {

    private static final String TAG = QuoteDowloadByRetrofit.class.toString();

    public QuoteDowloadByRetrofit() {
        super("QuoteDowloadByRetrofit");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"onHandleIntent");
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl( "http://quotesondesign.com/wp-json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuoteService quoteService = retrofit.create(QuoteService.class);
        quoteService.getListQuote(10).enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                Log.d(TAG,"onResponse");
                List<Quote> quoteList = response.body();
                for (Quote quote : quoteList) {
                    DbContext.getInstance().addQuote(quote);
                }
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                Log.d(TAG,"onFailure");
            }
        });
    }
}
