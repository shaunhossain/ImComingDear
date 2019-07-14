package com.shaunhossain.imcomingdear.data.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.User;
import com.shaunhossain.imcomingdear.data.testviewgroup.SwipeView;
import com.shaunhossain.imcomingdear.data.view.adapter.SwipeAdapter;
import com.shaunhossain.imcomingdear.data.view.viewmodel.UserViewModel;

import java.util.Random;

/**
 * Created by adriaboschsaez on 17/11/2017.
 */

public class DiscoveryFragment extends Fragment implements SwipeView.SwipeViewListener {

    public static Fragment newInstance() {

        Fragment fragment = new DiscoveryFragment();
        return fragment;
    }

    private UserViewModel userViewModel;

    private SwipeView swipeView;
    private SwipeAdapter swipeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.title_discovery);

        userViewModel = ViewModelProviders.of(getActivity()).get(UserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_discovery, container, false);

        setUpSwipeViewGroup(view);

        return view;
    }

    private void setUpSwipeViewGroup(View view) {

        swipeView = view.findViewById(R.id.fragment_profile_swipeview);
        swipeView.setSwipeViewListener(this);
        swipeAdapter = new SwipeAdapter(getContext());
        swipeView.setAdapter(swipeAdapter);

        userViewModel.getUserList().observe(this, userList -> {

            swipeAdapter.setContent(swipeView.getCurrentPosition(), userList);
            swipeAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onTopViewSwipedToLeft(int position) {

    }

    @Override
    public void onTopViewSwipedToRight(int position) {

        Random random = new Random();
        int number = random.nextInt(5);

        if (number % 5 != 0)
            return;

        //Remove actual dialog fragment if exist
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        android.app.Fragment fragment = fm.findFragmentByTag(getString(R.string.fragment_dialog_match));
        if (fragment != null) {
            ft.remove(fragment);
        }
        ft.addToBackStack(null);


        MatchDialog matchDialog = MatchDialog.newInstance(swipeAdapter.getItem(position));
        matchDialog.show(ft, getString(R.string.fragment_dialog_match));
    }

    @Override
    public void onTopViewTouched(int position) {

        User user = swipeAdapter.getItem(position);

        SwipeAdapter.ViewHolder viewHolder = (SwipeAdapter.ViewHolder) swipeView.getTopView().getTag();
        ImageView imageView = viewHolder.userImage;

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), imageView,
                        ViewCompat.getTransitionName(imageView));


        Intent intent = new Intent(getActivity(), UserDetailActivity.class);

        intent.putExtra(getString(R.string.intent_extra_user), user);

        startActivity(intent, options.toBundle());

    }

    @Override
    public void onViewAboutToEmpty() {

        userViewModel.requestData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_discovery, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_settings:

                startActivity(new Intent(getActivity(), DiscoverySettingsActivity.class));
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}