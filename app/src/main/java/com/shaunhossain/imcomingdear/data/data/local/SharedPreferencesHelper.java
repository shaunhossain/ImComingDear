package com.shaunhossain.imcomingdear.data.data.local;

import android.content.SharedPreferences;

/**
 * Created by adriaboschsaez on 22/02/2018.
 */

public class SharedPreferencesHelper {

    private SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {

        this.sharedPreferences = sharedPreferences;
    }

    public void save(Object o, String key){}

    public Object get(String key){
        return null;
    }
}
