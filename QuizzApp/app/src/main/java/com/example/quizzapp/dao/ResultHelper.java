package com.example.quizzapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizzapp.model.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultHelper {
    private QuizzHelper helper;

    public ResultHelper(Context context) {
        this.helper = new QuizzHelper(context);
    }

    public long addResult(Result result) {
        ContentValues values = getValuesFromResult(result);
        SQLiteDatabase database = helper.getWritableDatabase();
        return database.insert("result", null, values);
    }

    public List<Result> getResultByUserId(String userId){
        List<Result> list = new ArrayList<>();
        String whereClause = "user_id like ?";
        String[] whereArgs = {userId};
        SQLiteDatabase sqlite = helper.getReadableDatabase();
        Cursor cr = sqlite.query("result", null, whereClause, whereArgs, null, null, null);
        while (cr != null && cr.moveToNext()) {
            addResultFromCursor(cr, list);
        }
        return list;
    }

    private ContentValues getValuesFromResult(Result result) {
        ContentValues values = new ContentValues();
        values.put("score", result.getScore());
        values.put("user_id", result.getUserId());
        values.put("datetime", result.getDatetime());
        return values;

    }

    public void addResultFromCursor(Cursor cursor, List<Result> list) {
        int id = cursor.getInt(0);
        String userId = cursor.getString(1);
        String score = cursor.getString(2);
        String datetime = cursor.getString(3);
        list.add(new Result(id, score, userId, datetime));
    }

    public void deleteResultById(int id) {
        String whereClause = "_id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        sqlite.delete("result", whereClause, whereArgs);
    }
}
