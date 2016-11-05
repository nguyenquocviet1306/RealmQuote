package com.example.admin.dailyquote2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.example.admin.dailyquote2.Fragment.LoginFragment;
import com.example.admin.dailyquote2.Fragment.QuoteFragment;
import com.example.admin.dailyquote2.R;
import com.example.admin.dailyquote2.managers.Preferences;
import com.example.admin.dailyquote2.models.FragmentEvent;
import com.example.admin.dailyquote2.services.UnplashDowloadService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = MainActivity.class.toString() ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        if (Preferences.getInstance().getUsername() == null){
            changeFragment(new LoginFragment(), false);
        } else {
            changeFragment(new QuoteFragment(), false);

        }
    /* if(isConnectToInternet() {
        loadOnline();
        prepareOfflineData();
        } else {
        loadOffline();
        }
    * */
//        Storage storage = SimpleStorage.getInternalStorage(this);
//        storage.createFile("text","quote.txt","No pain, no gain");
//        String content = storage.readTextFile("text","quote.txt");
//        Log.d(TAG, String.format("Read done %s : ", content ));
        Intent intent = new Intent(this, UnplashDowloadService.class);
        startService(intent);
    }
    @Subscribe
    public void onEvent(FragmentEvent fragmentEvent){
        changeFragment(fragmentEvent.getFragment(),fragmentEvent.isAddToBackStack());
    }
    public void changeFragment(Fragment fragment, boolean AddToBackStack){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,fragment);

        if (AddToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }



}
