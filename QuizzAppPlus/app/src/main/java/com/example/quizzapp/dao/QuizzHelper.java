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
    private static int DATABASE_VERSION = 2;

    public QuizzHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE result(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id TEXT, score INTEGER, datetime TEXT)";
        db.execSQL(sql1);
        String sql2 = "CREATE TABLE question_done(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "option1 TEXT, option2 TEXT, option3 TEXT, option4 TEXT, question TEXT, answer TEXT," +
                "selected TEXT, result_id INT," +
                "FOREIGN KEY (result_id) REFERENCES result(_id))";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
