package com.shaunhossain.imcomingdear.data.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shaunhossain.imcomingdear.R;

/**
 * Created by adriaboschsaez on 16/02/2018.
 */

public class ProfileFragment extends Fragment {

    public static Fragment newInstance() {

        Fragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.title_profile);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initializeUI(view);

        return view;
    }

    private void initializeUI(View view) {

        ImageView imageView = view.findViewById(R.id.fragment_profile_image);
        TextView textView = view.findViewById(R.id.fragment_profile_text);
        Button button = view.findViewById(R.id.fragment_profile_button);

        Glide.with(getActivity())
                .load("http://estaticos01.elmundo.es/blogs/elmundo/happyfm/imagenes_posts/2013/10/28/76506_540x690.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);

        textView.setText("Adrià, 20");

        button.setOnClickListener((v) -> {

            startActivity(new Intent(getActivity(), EditProfileActivity.class));
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_profile, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_settings:

                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
