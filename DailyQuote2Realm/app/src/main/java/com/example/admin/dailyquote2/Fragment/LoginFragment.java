package com.example.admin.dailyquote2.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.example.admin.dailyquote2.R;
import com.example.admin.dailyquote2.managers.Preferences;
import com.example.admin.dailyquote2.models.FragmentEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.et_username)
    EditText etUserName;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.bt_save)
        public void onClick(View view){
            //1. Get string from et username
                String username = etUserName.getText().toString();
            //2. Save data to SharePrefence
        Preferences.getInstance().putUsername(username);
            //3. Transit to quote Fragment
        EventBus.getDefault().post(new FragmentEvent(
                new QuoteFragment(),
                false
        ));


    }


}
