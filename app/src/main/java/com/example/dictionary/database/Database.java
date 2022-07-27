package com.example.dictionary.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dictionary.model.Model;
import com.example.dictionary.libs.DataBaseHelper;
//import com.example.phone_db.libs.DataBaseHelper;
//import com.example.phone_db.model.Group;
//import com.example.phone_db.model.Student;
//import com.example.phone_db.model.Teacher;

import java.io.IOException;
import java.util.ArrayList;

//import uz.micro.star.lesson_17.libs.DataBaseHelper;
//import uz.micro.star.lesson_17.models.Student;

/**
 * Created by Microstar on 21.05.2019.
 */

public class Database {
    private static final String TAG = "TAG";
    private static Database database;
    private final Context mContext;
    private final DataBaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public Database(Context mContext) {
        this.mContext = mContext;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public static Database init(Context context) {
        if (database == null) {
            database = new Database(context);
        }
        return database;
    }

    public static Database getDatabase() {
        return database;
    }

    public Database createDatabase() {
        try {
            mDbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Database open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException);
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    @SuppressLint("Range")
    public ArrayList<Model> getWords(int id) {
        ArrayList<Model> data = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("select * from yangi where til_id = "+id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data.add(new Model(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("suz")),
                    cursor.getString(cursor.getColumnIndex("tarjima")),
                    cursor.getInt(cursor.getColumnIndex("favourite")),
                    cursor.getInt(cursor.getColumnIndex("til_id"))));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }

    @SuppressLint("Range")
    public ArrayList<Model> getFavouriteWords(int id) {
        ArrayList<Model> data = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("select * from yangi where favourie = "+id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data.add(new Model(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("suz")),
                    cursor.getString(cursor.getColumnIndex("tarjima")),
                    cursor.getInt(cursor.getColumnIndex("favourite")),
                    cursor.getInt(cursor.getColumnIndex("til_id"))));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }


    @SuppressLint("Range")
    public ArrayList<Model> sortFavourite(int favourite) {
        ArrayList<Model> data = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("select * from yangi where favourite = " + favourite, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data.add(new Model(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("suz")),
                    cursor.getString(cursor.getColumnIndex("tarjima")),
                    cursor.getInt(cursor.getColumnIndex("favourite")),
                    cursor.getInt(cursor.getColumnIndex("til_id"))));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }

    @SuppressLint("Range")
    public ArrayList<Model> searchFavourite(String letter,int id,int favourite) {
        ArrayList<Model> data = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("select * from yangi where suz like '"+letter+"%' and til_id = "+id+" and favourite = "+favourite, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data.add(new Model(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("suz")),
                    cursor.getString(cursor.getColumnIndex("tarjima")),
                    cursor.getInt(cursor.getColumnIndex("favourite")),
                    cursor.getInt(cursor.getColumnIndex("til_id"))));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }

    @SuppressLint("Range")
    public ArrayList<Model> search(String searchWord,int id) {
        ArrayList<Model> data = new ArrayList<>();
        Cursor cursor = mDb.rawQuery("select * from yangi where suz like '"+searchWord+"%' and til_id = "+id, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data.add(new Model(cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("suz")),
                    cursor.getString(cursor.getColumnIndex("tarjima")),
                    cursor.getInt(cursor.getColumnIndex("favourite")),
                    cursor.getInt(cursor.getColumnIndex("til_id"))));
            cursor.moveToNext();
        }
        cursor.close();
        return data;
    }

    public void editFavourite(Model userData,int id) {
        ContentValues values = new ContentValues();
        values.put("favourite", userData.getFavourite());
        mDb.update("yangi", values, "_id=" + id, null);
    }
}