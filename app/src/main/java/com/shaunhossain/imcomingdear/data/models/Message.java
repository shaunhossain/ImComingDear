package com.shaunhossain.imcomingdear.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by adriaboschsaez on 19/11/2017.
 */

@Entity(tableName = "messages")
public class Message {

    @PrimaryKey
    @NonNull
    private String time;

    private String text;

    private boolean isMine;

    public void setTime(String time) {
        this.time = time;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message() {

    }

    @Ignore
    public Message(String time, boolean isMine, String text) {

        this.time = time;
        this.isMine = isMine;
        this.text = text;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public String getTime() {
        return time;
    }

    public String getText() {

        return text;
    }

}
