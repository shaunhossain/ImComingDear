package com.shaunhossain.imcomingdear.data.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shaunhossain.imcomingdear.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adriaboschsaez on 17/02/2018.
 */

public class GridImageAdapter extends BaseAdapter {

    private Context context;
    private List<String> photoList;

    public GridImageAdapter(Context context, String[] photos) {
        this.context = context;
        this.photoList = Arrays.asList(photos);
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public String getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_grid_image, parent, false);
            imageView = convertView.findViewById(R.id.item_grid_image_imageview);
            convertView.setTag(imageView);

        } else {

            imageView = (ImageView) convertView.getTag();
        }

        Glide.with(context)
                .load(photoList.get(position))
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);

        return convertView;
    }
}
