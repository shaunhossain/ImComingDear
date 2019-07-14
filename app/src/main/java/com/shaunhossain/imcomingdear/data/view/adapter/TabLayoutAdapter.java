package com.shaunhossain.imcomingdear.data.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.view.DetailsFragment;
import com.shaunhossain.imcomingdear.data.view.GridImageFragment;

/**
 * Created by adriaboschsaez on 16/02/2018.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabLayoutAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0:
                return GridImageFragment.newInstance();
            case 1:
                return DetailsFragment.newInstance();
        }

        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch(position) {
            case 0:
                return context.getString(R.string.fragment_photos);
            case 1:
                return context.getString(R.string.fragment_details);
        }

        return null;
    }


    @Override
    public int getCount() {
        return 2;
    }
}