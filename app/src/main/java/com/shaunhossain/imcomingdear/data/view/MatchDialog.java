package com.shaunhossain.imcomingdear.data.view;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.User;
import com.shaunhossain.imcomingdear.data.utils.Utils;

public class MatchDialog extends DialogFragment {

    public static final String USER = "user";

    public static MatchDialog newInstance(User user) {

        MatchDialog matchDialog = new MatchDialog();
        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        matchDialog.setArguments(args);
        return matchDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_DialogWhenLarge);
    }

    @Override
    public void onResume() {

        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        int widthMargin = (int) Utils.convertDpToPixel(getResources().getDimension(R.dimen.dialog_width));
        int width = getResources().getDisplayMetrics().widthPixels - widthMargin;

        getDialog().getWindow().setLayout(width, height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_match, container, false);

        initializeUI(view);

        return view;
    }

    private void initializeUI(View view) {

        Bundle args = getArguments();
        User user = args.getParcelable(USER);

        ImageView myImageView = view.findViewById(R.id.image_me);
        Glide.with(getActivity())
                .load("http://estaticos01.elmundo.es/blogs/elmundo/happyfm/imagenes_posts/2013/10/28/76506_540x690.jpg")
                .apply(RequestOptions.circleCropTransform())
                .into(myImageView);

        ImageView yourImageView = view.findViewById(R.id.image_you);
        Glide.with(getActivity())
                .load(user.getPhotos()[0])
                .apply(RequestOptions.circleCropTransform())
                .into(yourImageView);

        TextView nameText = view.findViewById(R.id.text_user);
        nameText.setText("You and " + user.getName() + " have liked each other.");

        Button buttonCancel = view.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener((v) -> {
            dismiss();
        });

        Button buttonChat = view.findViewById(R.id.button_chat);
        buttonChat.setOnClickListener((v) -> {

            Intent intent = new Intent(getActivity(), ChatActivity.class);
            intent.putExtra(getString(R.string.intent_extra_user), user);
            startActivity(intent);
            dismiss();
        });
    }
}