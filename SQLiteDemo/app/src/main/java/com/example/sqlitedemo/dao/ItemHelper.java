package com.example.sqlitedemo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitedemo.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ChiTieu.db";
    private static int DATABASE_VERSION = 1;

    public ItemHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE item(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT, category TEXT, price TEXT, date TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Item> getAll() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase sqlite = getReadableDatabase();
        String order = "date DESC";
        Cursor cursor = sqlite.query("item", null, null, null, null, null, order);
        while (cursor != null && cursor.moveToNext()) {
            addItemFromCursor(cursor, itemList);
        }
        return itemList;
    }

    public List<Item> getByTitle(String key) {
        List<Item> list = new ArrayList<>();
        String whereClause = "title like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase sqlite = getReadableDatabase();
        Cursor cr = sqlite.query("item", null, whereClause, whereArgs, null, null, null);
        while (cr != null && cr.moveToNext()) {
            addItemFromCursor(cr, list);
        }
        return list;
    }

    public List<Item> getByCategory(String key) {
        List<Item> list = new ArrayList<>();
        String whereClause = "category like ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase sqlite = getReadableDatabase();
        Cursor cr = sqlite.query("item", null, whereClause, whereArgs, null, null, null);
        while (cr != null && cr.moveToNext()) {
            addItemFromCursor(cr, list);
        }
        return list;
    }

    public List<Item> getByDate(String date) {
        List<Item> list = new ArrayList<>();
        String whereClause = "date like ?";
        String[] whereArgs = {date};
        SQLiteDatabase sqlite = getReadableDatabase();
        Cursor cr = sqlite.query("item", null, whereClause, whereArgs, null, null, null);
        while (cr != null && cr.moveToNext()) {
            addItemFromCursor(cr, list);
        }
        return list;
    }
    public List<Item> getByTimeRange(String fromDate, String toDate) {
        List<Item> list = new ArrayList<>();
        String whereClause = "date BETWEEN ? AND ?";
        String[] whereArgs = {fromDate, toDate};
        SQLiteDatabase sqlite = getReadableDatabase();
        Cursor cr = sqlite.query("item", null, whereClause, whereArgs, null, null, null);
        while (cr != null && cr.moveToNext()) {
            addItemFromCursor(cr, list);
        }
        return list;
    }

    public void addItemFromCursor(Cursor cursor, List<Item> list) {
        int id = cursor.getInt(0);
        String title = cursor.getString(1);
        String category = cursor.getString(2);
        String price = cursor.getString(3);
        String date = cursor.getString(4);
        list.add(new Item(id, title, category, Double.valueOf(price), date));
    }

    public long addItem(Item item) {
        ContentValues values = new ContentValues();
        getValuesFromItem(values, item);
        SQLiteDatabase database = getWritableDatabase();
        return database.insert("item", null, values);

    }

    public int update(Item editItem, int oldId) {
        ContentValues values = new ContentValues();
        getValuesFromItem(values, editItem);
        SQLiteDatabase sqlite = getWritableDatabase();
        String whereClause = "_id = ?";
        String[] whereArgs = {Integer.toString(oldId)};
        return sqlite.update("item", values, whereClause, whereArgs);
    }

    public int deleteById(int id) {
        String whereClause = "_id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqlite = getWritableDatabase();
        return sqlite.delete("item", whereClause, whereArgs);
    }

    private void getValuesFromItem(ContentValues values, Item item) {
        values.put("title", item.getTitle());
        values.put("category", item.getCategory());
        values.put("price", item.getPrice());
        values.put("date", item.getDate());
    }
}
