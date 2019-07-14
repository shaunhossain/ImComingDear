package com.shaunhossain.imcomingdear.data.manager.impl;

import android.content.res.Resources;
import android.util.Log;

import com.shaunhossain.imcomingdear.R;
import com.shaunhossain.imcomingdear.data.models.Message;

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
 * Created by adriaboschsaez on 14/02/2018.
 */

public class XatManager {

    private List<Message> messageList;

    private Resources resources;

    public XatManager() {
    }

    public void setResources(Resources resources) {
        this.resources = resources;
        fillMessageListFromFile();
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void addMessage(Message message) {

        messageList.add(message);
    }


    private void fillMessageListFromFile() {
        try {

            String jsonContent = readJsonFile(R.raw.messages);
            if (jsonContent.isEmpty()) {
                return;
            }

            messageList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonContent);

            for (int index = 0; index < jsonArray.length(); ++index) {
                JSONObject jsonObject = jsonArray.getJSONObject(index);

                String time = jsonObject.getString("time");
                boolean isMine = jsonObject.getBoolean("isMine");
                String message = jsonObject.getString("message");

                messageList.add(new Message(time, isMine, message));
            }
        } catch (Exception e) {
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
        } catch (Exception e) {
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