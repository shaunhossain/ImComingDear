package com.shaunhossain.imcomingdear.data.utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.shaunhossain.imcomingdear.data.models.Message;

import java.util.List;

/**
 * Created by adriaboschsaez on 23/11/2017.
 */

@Dao
public interface MessageDao {

    @Query("SELECT * FROM messages")
    public Message[] loadAllMessages();

    @Query("SELECT * FROM messages WHERE time > :time")
    public Message[] loadAllMessagesOlderThan(int time);

    @Insert
    public void insertMessages(Message message);

    @Insert
    public void insertBothMessages(Message message1, Message message2);

    @Insert
    public void insertMessagesAndFriends(Message message, List<Message> friends);

    @Update
    public void updateUsers(Message... users);

    @Delete
    public void deleteUsers(Message... users);
}

