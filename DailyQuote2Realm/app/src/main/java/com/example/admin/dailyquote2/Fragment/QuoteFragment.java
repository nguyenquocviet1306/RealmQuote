package com.example.admin.dailyquote2.Fragment;


import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.admin.dailyquote2.R;
import com.example.admin.dailyquote2.constants.Constants;
import com.example.admin.dailyquote2.jsonmodels.QuoteJSONModel;
import com.example.admin.dailyquote2.managers.DbContext;
import com.example.admin.dailyquote2.managers.FileManager;
import com.example.admin.dailyquote2.managers.NetworkManager;
import com.example.admin.dailyquote2.managers.Preferences;
import com.example.admin.dailyquote2.models.Quote;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import static com.example.admin.dailyquote2.constants.Constants.QUOTE_API;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    private static final String TAG = QuoteFragment.class.toString();
    @BindView(R.id.inv_background)
    ImageView invBackground;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_usernam)
    TextView tvUserName;


    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        ButterKnife.bind(this, view);

        setupUI();
        return view;
    }


    private void updateQuote(final QuoteJSONModel quoteJSONModel) {
        Activity parent = getActivity();
        parent.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvTitle.setText(quoteJSONModel.getTitle());
                tvContent.setText(Html.fromHtml(quoteJSONModel.getContent()));
            }
        });
    }

    private void setupUI() {
        randomContent();

    }
    private void randomContent(){
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();
        String uri = Uri.fromFile(FileManager.getInstance().RandomImage()).toString();
        ImageLoader.getInstance().displayImage(
                uri,
                invBackground
        );
        Quote quote = DbContext.getInstance().getRandom();
        tvContent.setText(Html.fromHtml(quote.getContent()));
        tvTitle.setText(quote.getTitle());
    }


}
