package com.shaunhossain.imcomingdear.data.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.view.viewmodel.MatchViewModel;
import com.shaunhossain.imcomingdear.data.view.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (item) -> {

        int id = item.getItemId();

        Fragment fragment = null;
        Bundle bundle = new Bundle();

        switch (id) {
            case R.id.navigation_discovery:
                fragment = DiscoveryFragment.newInstance();
                break;
            case R.id.navigation_chat:
                fragment = ConversationsFragment.newInstance();
                break;
            case R.id.navigation_profile:
                fragment = ProfileFragment.newInstance();
                break;
        }

        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment).commit();
        return true;

    };

    public <T extends Number> T a(Class<? extends Integer> myClass) {
        Object o = null;
        try {
            o =  myClass.newInstance();
        }catch (Exception e) {

        }
        return (T) o;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.setResources(getResources());

        MatchViewModel matchViewModel = ViewModelProviders.of(this).get(MatchViewModel.class);
        matchViewModel.setResources(getResources());

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, new DiscoveryFragment()).commit();

        //String refreshedToken = FirebaseInstanceId.getInstance().getToken();

    }

}
