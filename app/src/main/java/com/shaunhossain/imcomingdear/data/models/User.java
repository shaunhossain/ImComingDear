package com.shaunhossain.imcomingdear.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by adriaboschsaez on 22/11/2017.
 */

@Entity(tableName = "users")
public class User implements Parcelable {

    @PrimaryKey
    public String _id;

    public String name;
    public int age;
    public String sex;
    public String[] photos;
    public String description;
    public String currentWork;
    public String college;
    public String favoriteSong;

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String[] getPhotos() {
        return photos;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrentWork() {
        return currentWork;
    }

    public String getCollege() {
        return college;
    }

    public String getFavoriteSong() {
        return favoriteSong;
    }

    public User(String _id, String name, int age, String sex, String[] photos, String description, String currentWork, String college, String favoriteSong) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.photos = photos;
        this.description = description;
        this.currentWork = currentWork;
        this.college = college;
        this.favoriteSong = favoriteSong;
    }

    private User(Parcel parcel) {

        _id = parcel.readString();
        name = parcel.readString();
        age = parcel.readInt();
        sex = parcel.readString();
        photos = parcel.createStringArray();
        description = parcel.readString();
        currentWork = parcel.readString();
        college = parcel.readString();
        favoriteSong = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(_id);
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(sex);
        parcel.writeStringArray(photos);
        parcel.writeString(description);
        parcel.writeString(currentWork);
        parcel.writeString(college);
        parcel.writeString(favoriteSong);
    }

    public static final Creator<User> CREATOR = new Creator<User>()
    {
        @Override
        public User createFromParcel(Parcel parcel)
        {
            return new User(parcel);
        }

        @Override
        public User[] newArray(int size)
        {
            return new User[size];
        }
    };


}
