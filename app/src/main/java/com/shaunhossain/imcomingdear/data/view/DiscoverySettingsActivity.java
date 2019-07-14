package com.shaunhossain.imcomingdear.data.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar;
import com.shaunhossain.imcomingdear.R;

/**
 * Created by adriaboschsaez on 17/02/2018.
 */

public class DiscoverySettingsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery_settings);

        initializeUI();
    }



    private void initializeUI() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CrystalRangeSeekbar rangeSeekbarAge = findViewById(R.id.range_seekbar_age);
        TextView textRangeAge = findViewById(R.id.text_range_age);

        rangeSeekbarAge.setOnRangeSeekbarChangeListener((Number minValue, Number maxValue) -> {

            String maxAge = (maxValue.intValue() == 60) ? maxValue.toString() + "+" : maxValue.toString();
            textRangeAge.setText(minValue.toString() + " - " + maxAge);
        });

        CrystalSeekbar rangeSeekbarDistance = findViewById(R.id.range_seekbar_distance);
        TextView textRangeDistance = findViewById(R.id.text_range_distance);

        rangeSeekbarDistance.setOnSeekbarChangeListener((Number value) ->
            textRangeDistance.setText(value.toString() + " km"));

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
