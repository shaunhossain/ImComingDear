package com.shaunhossain.imcomingdear.data.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriaboschsaez on 19/11/2017.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final String TAG = MessageAdapter.class.getSimpleName();

    public static final int NOT_MINE = 0;
    public static final int MINE = 1;

    private Context context;
    private List<Message> messageList;

    public MessageAdapter (Context context) {

        this.context = context;
        messageList = new ArrayList<>();
    }

    public void addContent(List<Message> newMessageList) {

        if (messageList.size() == 0) {

            messageList = newMessageList;
            notifyDataSetChanged();
            return;
        }

        int size = messageList.size();

        List<Message> addList = newMessageList.subList(size, newMessageList.size());

        messageList.addAll(addList);
        notifyItemRangeInserted(size, addList.size());
    }

    public void addItem(Message messsage) {

        messageList.add(messsage);
        notifyItemInserted(messageList.size() - 1);
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getIsMine() ? MINE : NOT_MINE;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layout = (viewType == MINE) ? R.layout.item_message_mine : R.layout.item_message;

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Message message = messageList.get(position);

        holder.textMessage.setText(message.getText());
        holder.textTime.setText(message.getTime());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textMessage;
        private TextView textTime;

        public ViewHolder(View itemView) {
            super(itemView);

            textMessage = itemView.findViewById(R.id.item_message_text);
            textTime = itemView.findViewById(R.id.item_message_time);
        }

    }
}



