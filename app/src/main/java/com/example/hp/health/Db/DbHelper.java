package com.example.hp.health.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hp.health.Model.Utility;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by hp on 23/04/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HealthDB";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalUser.CREATE_STATEMENT);
        db.execSQL(LocalSteps.CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + LocalUser.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + LocalSteps.TABLE_NAME);
        onCreate(db);
    }

    public void addUser(LocalUser user) throws NoSuchAlgorithmException {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LocalUser.NAME, user.getName());
        values.put(LocalUser.PASSWORD, user.getPassword());
        values.put(LocalUser.DATE, user.getDate());

        db.insert(LocalUser.TABLE_NAME, null, values);
    }

    public HashMap<Long, LocalUser> getAllUser() {
        HashMap<Long, LocalUser> users = new LinkedHashMap<Long, LocalUser>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM" + LocalUser.TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                LocalUser user = new LocalUser(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                users.put(user.getId(), user);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return users;
    }
    public LocalUser getLocalUserByUserid(String userid){
        LocalUser users = new LocalUser();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM" + LocalUser.TABLE_NAME+ "WHERE" + LocalUser.USER_ID +"=" +userid,null);

        LocalUser user = new LocalUser(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        cursor.close();
        return user;
    }

    public int getuserID(String username){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + LocalUser.TABLE_NAME + " WHERE " + LocalUser.NAME + " = '" + username + "'", null);
        int userid = 0;
        if (cursor.moveToFirst())
            userid = cursor.getInt(0);
        cursor.close();
        return userid;
    }

    public LocalSteps getRecordByUsername (int userid){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM" +LocalSteps.TABLE_NAME+ "WHERE" + LocalSteps.USER_ID+ "="+ userid,null);

        if (cursor.moveToFirst()){
            if (cursor == null){
                System.out.println("null");
                return null;
            }else {
                System.out.println("....");
                LocalSteps s = new LocalSteps(cursor.getInt(0),cursor.getString(1),cursor.getLong(2),cursor.getInt(3));
                cursor.close();
                return s;
            }

        }else {
            System.out.println("?????");
            cursor.close();
            return null;
        }
    }

    public void addSteps(LocalSteps steps){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LocalSteps.STEPS,steps.getStep());
        values.put(LocalSteps.TIME,steps.getTime());
        values.put(LocalSteps.USER_ID,steps.getUserId());
        db.insert(LocalSteps.TABLE_NAME,null,values);


    }
    public void updateLocalSteps(String username,String Steps){

        int userid = getuserID(username);
        Long newsteps = Long.parseLong(Steps);
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "update" +LocalSteps.TABLE_NAME+"set"+LocalSteps.STEPS+"="+newsteps+"WHERE"+LocalSteps.USER_ID+"="+userid;
        db.execSQL(sql);
    }



    public long getTotalLocalSteps(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        long steps = 0;
        int userid = getuserID(username);
        Cursor cursor = db.rawQuery("SELECT * FROM "+ LocalSteps.TABLE_NAME +" WHERE "+ LocalSteps.USER_ID+" = "
                +userid + " AND " + LocalSteps.TIME +" LIKE '" + Utility.Today()+"%'",null);
        if (cursor.moveToFirst()){
            do {
                steps +=cursor.getLong(2);
            }while (cursor.moveToNext());
        }

        cursor.close();
        System.out.println( "Total Steps In LocalDB"+ steps);

        return steps;

    }

}


