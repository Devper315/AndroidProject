package com.example.quizzapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizzapp.model.QuestionDone;
import com.example.quizzapp.model.Result;

import java.util.ArrayList;
import java.util.List;

public class QuestionDoneHelper {
    private QuizzHelper helper;
    public QuestionDoneHelper (Context context){
        this.helper = new QuizzHelper(context);
    }

    public void addDoneList(List<QuestionDone> doneList, Long resultId){
        List<ContentValues> valuesList = getValuesFromDoneList(doneList, resultId);
        SQLiteDatabase database = helper.getWritableDatabase();
        for(ContentValues values: valuesList){
            database.insert("question_done", null, values);
        }
    }

    public List<QuestionDone> getDoneListByResult(Result result){
        List<QuestionDone> list = new ArrayList<>();
        String whereClause = "result_id = ?";
        String[] whereArgs = {String.valueOf(result.getId())};
        SQLiteDatabase sqlite = helper.getReadableDatabase();
        Cursor cr = sqlite.query("question_done", null, whereClause, whereArgs, null, null, null);
        while (cr != null && cr.moveToNext()) {
            addDoneFromCursor(cr, list);
        }
        return list;
    }

    public void addDoneFromCursor(Cursor cursor,  List<QuestionDone> list){
        int id = cursor.getInt(0);
        String option1 = cursor.getString(1);
        String option2 = cursor.getString(2);
        String option3 = cursor.getString(3);
        String option4 = cursor.getString(4);
        String question = cursor.getString(5);
        String answer = cursor.getString(6);
        String selected = cursor.getString(7);
        list.add(new QuestionDone(id, option1, option2, option3, option4, question, answer, selected));
    }

    private List<ContentValues> getValuesFromDoneList(List<QuestionDone> doneList, Long resultId){
        List<ContentValues> valuesList = new ArrayList<>();
        for(QuestionDone done: doneList){
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

    public void deleteById(int resultId) {
        String whereClause = "result_id = ?";
        String[] whereArgs = {Integer.toString(resultId)};
        SQLiteDatabase sqlite = helper.getWritableDatabase();
        sqlite.delete("question_done", whereClause, whereArgs);
    }
}
