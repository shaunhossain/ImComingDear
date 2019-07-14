package com.shaunhossain.imcomingdear.data.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriaboschsaez on 22/11/2017.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    public static final String TAG = ConversationAdapter.class.getSimpleName();

    private Context context;
    private List<User> userList;
    private List<User> userListCopy;
    private View.OnClickListener listener;

    public ConversationAdapter(Context context) {

        this.context = context;
        this.userList = new ArrayList<>();
        this.userListCopy = new ArrayList<>();
    }

    public void setListener(View.OnClickListener listener) {

        this.listener = listener;
    }

    public void setContent(List<User> newMatchList) {

        userListCopy = newMatchList;
        userList.clear();
        userList.addAll(newMatchList);
    }

    public User getItemAt(int position) {

        return userList.get(position);
    }

    public void filter(String query) {

        if (query.isEmpty() && userList.size() == userListCopy.size())
            return;

        userList.clear();

        if (query.isEmpty())
            userList.addAll(userListCopy);

        else {
            for (User user : userListCopy) {

                if (user.getName().toLowerCase().contains(query.toLowerCase()))
                    userList.add(user);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation, parent, false);

        view.setOnClickListener(listener);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        User user = userList.get(position);

        Glide.with(context)
                .load(user.photos[0])
                .apply(RequestOptions.circleCropTransform())
                .into(holder.userImage);

        holder.userName.setText(user.getName());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView userImage;
        public TextView userName;

        public ViewHolder(View view) {
            super(view);

            userImage = view.findViewById(R.id.item_conversation_image);
            userName = view.findViewById(R.id.item_conversation_name);

        }
    }
}
