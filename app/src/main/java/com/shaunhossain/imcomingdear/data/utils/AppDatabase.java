package com.shaunhossain.imcomingdear.data.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shaunhossain.imcomingdear.data.models.Message;

/**
 * Created by adriaboschsaez on 23/11/2017.
 */

    @Database(entities = {Message.class}, version = 1,exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {

    public abstract MessageDao messageDao();


    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "message-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}