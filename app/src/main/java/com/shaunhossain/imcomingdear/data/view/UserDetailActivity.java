package com.shaunhossain.imcomingdear.data.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.User;
import com.shaunhossain.imcomingdear.data.view.adapter.ImageAdapter;

public class UserDetailActivity extends AppCompatActivity {

    public static final String TAG = UserDetailActivity.class.getSimpleName();

    private User user;

    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        // Stop the activity loading before Glide has finished loading the image.
        supportPostponeEnterTransition();

        user = getIntent().getParcelableExtra(getString(R.string.intent_extra_user));

        initializeUI();
    }

    private void initializeUI() {

        //Set up toolbar
        Toolbar toolbar = findViewById(R.id.activity_user_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(user.getName() + ", " + user.getAge());


        ViewPager viewPager = findViewById(R.id.activity_user_viewpager);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewPager.setTransitionName(user._id);
        }
        viewPager.setAdapter(new ImageAdapter(getSupportFragmentManager(), user.photos));

        TextView descriptionTxt = findViewById(R.id.description);
        TextView currentWorkTxt = findViewById(R.id.work);
        TextView collegeTxt = findViewById(R.id.college);
        TextView songTxt = findViewById(R.id.song);

        descriptionTxt.setText(user.getDescription());
        currentWorkTxt.setText(user.getCurrentWork());
        collegeTxt.setText(user.getCollege());
        songTxt.setText(user.getFavoriteSong());
    }

    @Override
    public void onBackPressed() {
        finish();
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
