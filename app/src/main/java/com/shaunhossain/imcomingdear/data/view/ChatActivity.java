package com.shaunhossain.imcomingdear.data.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.Message;
import com.shaunhossain.imcomingdear.data.models.User;
import com.shaunhossain.imcomingdear.data.view.adapter.MessageAdapter;
import com.shaunhossain.imcomingdear.data.view.viewmodel.XatViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    public static final String TAG = ChatActivity.class.getSimpleName();

    XatViewModel xatViewModel;

    private User user;

    private SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        xatViewModel = ViewModelProviders.of(this).get(XatViewModel.class);
        xatViewModel.setResources(getResources());

        user = getIntent().getParcelableExtra(getString(R.string.intent_extra_user));

        initUI();

    }

    private void initUI() {

        //Set up toolbar_chat
        Toolbar toolbar = findViewById(R.id.activity_chat_toolbar);

        ImageView userImgView = toolbar.findViewById(R.id.toolbar_image_view);
        TextView userTxtView = toolbar.findViewById(R.id.toolbar_text_view);

        Glide.with(this)
                .load(user.photos[0])
                .apply(RequestOptions.circleCropTransform())
                .into(userImgView);

        userTxtView.setText(user.getName());

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);


        RecyclerView xatRecycle = findViewById(R.id.activity_chat_recyclerview);
        MessageAdapter messageAdapter = new MessageAdapter(this);

        xatRecycle.setAdapter(messageAdapter);
        xatRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        xatViewModel.getMessageList().observe(this, (messageList) -> {

            messageAdapter.addContent(messageList);
            xatRecycle.scrollToPosition(messageAdapter.getItemCount() - 1);
        });

        EditText messageEdit = findViewById(R.id.activity_chat_edittext);
        messageEdit.setOnClickListener((View v) -> {

            Thread thread = new Thread(() -> {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                runOnUiThread(() -> xatRecycle.scrollToPosition(messageAdapter.getItemCount() - 1));

            });
            thread.start();
        });


        Button button = findViewById(R.id.activity_chat_button);
        button.setOnClickListener((v) -> {

            String text = messageEdit.getText().toString();
            if (text.isEmpty())
                return;

            String time = dateformat.format(new Date());
            Message message = new Message(time, true, text);

            messageAdapter.addItem(message);

            xatRecycle.scrollToPosition(messageAdapter.getItemCount() - 1);

            messageEdit.setText(null);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}


