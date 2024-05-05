package com.example.quizzapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.quizzapp.model.Result;
import com.example.quizzapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class QuizzHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quizz.db";
    private static int DATABASE_VERSION = 1;

    public QuizzHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, firebase_id TEXT)";
        db.execSQL(sql1);
        String sql2 = "CREATE TABLE result(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id TEXT, score INTEGER, datetime TEXT)";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addUser(User user) {
        ContentValues values = new ContentValues();
        getValuesFromUser(values, user);
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("user", null, values);
    }

    public long addResult(Result result) {
        ContentValues values = new ContentValues();
        getValuesFromResult(values, result);
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("result", null, values);
    }

    private void getValuesFromUser(ContentValues values, User user) {
        values.put("name", user.getName());
        values.put("firebase_id", user.getFirebaseId());
    }

    private void getValuesFromResult(ContentValues values, Result result) {
        values.put("score", result.getScore());
        values.put("user_id", result.getUserId());
        values.put("datetime", result.getDatetime());

    }

    public boolean checkUserByFirebaseId(String firebaseId) {
        String whereClause = "firebase_id like ?";
        String[] whereArgs = {firebaseId};
        SQLiteDatabase sqlite = getReadableDatabase();
        Cursor cr = sqlite.query("user", null, whereClause, whereArgs, null, null, null);
        return cr != null && cr.moveToFirst();
    }

    public void addUserFromCursor(Cursor cursor, List<User> list) {
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String firebaseId = cursor.getString(2);
        list.add(new User(id, name, firebaseId));
    }

    public void addResultFromCursor(Cursor cursor, List<Result> list) {
        int id = cursor.getInt(0);
        String userId = cursor.getString(1);
        int score = cursor.getInt(2);
        String datetime = cursor.getString(3);
        list.add(new Result(id, score, userId, datetime));
    }
}
