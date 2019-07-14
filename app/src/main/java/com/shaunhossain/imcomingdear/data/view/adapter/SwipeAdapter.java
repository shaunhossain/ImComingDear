package com.shaunhossain.imcomingdear.data.view.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.User;
import com.shaunhossain.imcomingdear.data.testviewgroup.SwipeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriaboschsaez on 06/02/2018.
 */

public class SwipeAdapter extends BaseAdapter {

    public static final String TAG = SwipeAdapter.class.getSimpleName();

    private Context context;
    private List<User> userList;

    public SwipeAdapter(Context context) {

        this.context = context;
        userList = new ArrayList<>();
    }

    public void setContent(int currentPosition , List<User> newUserList) {

        userList = userList.subList(currentPosition, userList.size());
        userList.addAll(userList.size(), newUserList);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public User getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_swipe, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }
        else {

            holder = (ViewHolder) convertView.getTag();
        }

        User user = userList.get(position);

        Glide.with(context)
             .load(user.photos[0])
             .apply(RequestOptions.centerCropTransform())
             .into(holder.userImage);

        holder.userNameAge.setText(user.getName() + ", " + user.age);

        final View view = convertView;
        holder.dislikeButton.setOnClickListener((v) -> ((SwipeView) parent).swipeToLeft(view));
        holder.likeButton.setOnClickListener((v) -> ((SwipeView) parent).swipeToRight(view));

        ViewCompat.setTransitionName(holder.userImage, user._id);

        return convertView;
    }


    public static class ViewHolder {

        public ImageView userImage;
        public TextView userNameAge;
        public Button dislikeButton;
        public Button likeButton;
        public View dislikeView;
        public View likeView;

        public ViewHolder(View view) {

            userImage = view.findViewById(R.id.component_swipe_image);
            userNameAge = view.findViewById(R.id.component_swipe_textview);
            dislikeButton = view.findViewById(R.id.component_swipe_button_dislike);
            likeButton = view.findViewById(R.id.component_swipe_button_like);
            dislikeView = view.findViewById(R.id.component_swipe_view_dislike);
            likeView = view.findViewById(R.id.component_swipe_view_like);
        }


    }

}