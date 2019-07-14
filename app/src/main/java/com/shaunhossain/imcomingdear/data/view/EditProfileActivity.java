package com.shaunhossain.imcomingdear.data.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.view.adapter.TabLayoutAdapter;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        initializeUI();
    }

    private void initializeUI() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = findViewById(R.id.tab_layout);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(), this));

        tabLayout.setupWithViewPager(viewPager);

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
