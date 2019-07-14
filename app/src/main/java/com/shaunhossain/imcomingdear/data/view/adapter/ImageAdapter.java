package com.shaunhossain.imcomingdear.data.view.adapter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.shaunhossain.imcomingdear.R;

/**
 * Created by adriaboschsaez on 10/02/2018.
 */

public class ImageAdapter extends FragmentPagerAdapter {

    private String[] photos;

    public ImageAdapter(FragmentManager fm, String[] photos) {
        super(fm);
        this.photos = photos;
    }

    @Override
    public int getCount() {
        return photos.length;
    }

    @Override
    public Fragment getItem(int position) {

        return ImageFragment.newInstance(photos[position]);
    }


    public static class ImageFragment extends Fragment {

        private static final String URL = "url";

        public ImageFragment() { }


        public static ImageFragment newInstance(String url) {
            ImageFragment fragment = new ImageFragment();
            Bundle args = new Bundle();
            args.putString(URL, url);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.item_image, container, false);

        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            String url = getArguments().getString(URL);

            ImageView imageView = view.findViewById(R.id.item_image_imageview);

            Glide.with(this)
                    .load(url)
                    .apply(RequestOptions.centerCropTransform())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                            getActivity().supportStartPostponedEnterTransition();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            getActivity().supportStartPostponedEnterTransition();
                            return false;
                        }
                    })
                    .into(imageView);
        }

    }
}
