package com.shaunhossain.imcomingdear.data.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.shaunhossain.imcomingdear.R;

/**
 * Created by adriaboschsaez on 16/02/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeUI();
    }



    private void initializeUI() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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