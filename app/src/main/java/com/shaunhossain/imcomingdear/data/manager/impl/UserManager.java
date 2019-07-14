package com.shaunhossain.imcomingdear.data.manager.impl;

import android.content.res.Resources;
import android.util.Log;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriaboschsaez on 08/02/2018.
 */

public class UserManager {

    private List<User> userList;

    private Resources resources;

    public UserManager() {
    }

    public List<User> getUsersList() {
        return userList;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
        fillUserListFromFile();
    }

    private void fillUserListFromFile() {
        try {

            String jsonContent = readJsonFile(R.raw.users);
            if(jsonContent.isEmpty()) {
                return;
            }

            userList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonContent);

            for(int index = 0; index < jsonArray.length(); ++index)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(index);

                String _id = jsonObject.getString("_id");
                String name = jsonObject.getString("name");
                int age = jsonObject.getInt("age");
                String sex = jsonObject.getString("sex");

                JSONArray arrayPhotos = jsonObject.getJSONArray("photos");
                String[] photos = new String[arrayPhotos.length()];
                for (int i = 0; i < arrayPhotos.length(); i++) {
                    photos[i] = arrayPhotos.optString(i);
                }

                String description = jsonObject.getString("description");
                String currentWork = jsonObject.getString("currentWork");
                String college = jsonObject.getString("college");
                String favoriteSong = jsonObject.getString("favoriteSong");

                userList.add(new User(_id, name, age, sex, photos, description, currentWork, college, favoriteSong));
            }
        }
        catch (Exception e) {
            Log.e(this.getClass().getName(), e.getMessage());
        }
    }

    private String readJsonFile(int file) {
        InputStream is = resources.openRawResource(file);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        }
        catch (Exception e) {
            Log.e(this.getClass().getName(), e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(this.getClass().getName(), e.getMessage());
            }
        }

        String jsonString = writer.toString();
        return jsonString;
    }
}