package com.example.quizzapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizzapp.model.UserAnswer;

import java.util.ArrayList;
import java.util.List;

public class UserAnswerHelper {
    private QuizzHelper helper;
    public UserAnswerHelper(Context context){
        this.helper = new QuizzHelper(context);
    }

    public void addDoneList(List<UserAnswer> doneList, Long resultId){
        List<ContentValues> valuesList = getValuesFromDoneList(doneList, resultId);
        SQLiteDatabase database = helper.getWritableDatabase();
        for(ContentValues values: valuesList){
            database.insert("user_answer", null, values);
        }
    }

    public List<UserAnswer> getDoneListByResultId(int resultId){
        List<UserAnswer> list = new ArrayList<>();
        String whereClause = "result_id = ?";
        String[] whereArgs = {String.valueOf(resultId)};
        SQLiteDatabase sqlite = helper.getReadableDatabase();
        Cursor cr = sqlite.query("user_answer", null, whereClause, whereArgs, null, null, null);
        while (cr != null && cr.moveToNext()) {
            addDoneFromCursor(cr, list);
        }
        return list;
    }

    public void addDoneFromCursor(Cursor cursor,  List<UserAnswer> list){
        int id = cursor.getInt(0);
        String option1 = cursor.getString(1);
        String option2 = cursor.getString(2);
        String option3 = cursor.getString(3);
        String option4 = cursor.getString(4);
        String question = cursor.getString(5);
        String answer = cursor.getString(6);
        String selected = cursor.getString(7);
        list.add(new UserAnswer(id, option1, option2, option3, option4, question, answer, selected));
    }

    private List<ContentValues> getValuesFromDoneList(List<UserAnswer> doneList, Long resultId){
        List<ContentValues> valuesList = new ArrayList<>();
        for(UserAnswer done: doneList){
            ContentValues values = new ContentValues();
            values.put("question", done.getQuestion());
            values.put("option1", done.getOption1());
            values.put("option2", done.getOption2());
            values.put("option3", done.getOption3());
            values.put("option4", done.getOption4());
            values.put("answer", done.getAnswer());
            values.put("selected", done.getSelected());
            values.put("result_id", resultId);
            valuesList.add(values);
        }
        return valuesList;
    }

    public void deleteByResultId(int resultId) {
        String whereClause = "result_id = ?";
        String[] whereArgs = {Integer.toString(resultId)};
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        sqlite.delete("user_answer", whereClause, whereArgs);
    }
}
