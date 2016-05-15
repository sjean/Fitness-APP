package com.example.hp.health.Db;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hp on 23/04/2016.
 */
public class LocalUser implements Parcelable {

    public static final String TABLE_NAME = "localuser";
    public static final String USER_ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String DATE = "date";

    public static final String CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            NAME + " TEXT NOT NULL, " +
            PASSWORD + " TEXT NOT NULL, " +
            DATE + " TEXT NOT NULL" +
            ")";
    private long id;
    private String name;
    private String password;
    private String date;

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel in) {
            return new LocalUser(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new LocalUser[size];
        }
    };
    // Parcel constructor, reads in values in the order they were written
    public LocalUser(Parcel in) {
        this.date = in.readString();
        this.password = in.readString();
        this.name = in.readString();
        this.id = in.readLong();
    }
    // Default constructor with required params
    public LocalUser( long id, String name,String password, String date) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.date = date;
    }
    public LocalUser( String name,String password, String date) {
        this.name = name;
        this.password = password;
        this.date = date;
    }
    public LocalUser() {
        this.name = name;
        this.password = password;
        this.date = date;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
