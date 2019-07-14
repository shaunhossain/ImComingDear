package com.shaunhossain.imcomingdear.data.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaunhossain.imcomingdear.R;

/**
 * Created by adriaboschsaez on 16/02/2018.
 */

public class DetailsFragment extends Fragment {

    public static Fragment newInstance() {

        Fragment detailsFragment = new DetailsFragment();
        return detailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        return view;
    }
}
