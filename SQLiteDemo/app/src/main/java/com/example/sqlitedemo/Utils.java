package com.example.sqlitedemo;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.sqlitedemo.model.Item;

import java.util.Calendar;
import java.util.List;

public class Utils {
    public static DatePickerDialog buildDateDialog(Context context, EditText view){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                String date = String.format("%02d/%02d/%d", d, m + 1, y);
                view.setText(date);
            }
        }, year, month, day);
        return dialog;
    }

    public static Double getSumPrice(List<Item> itemList){
        double sum = 0D;
        for (Item item: itemList) sum += item.getPrice();
        return sum;
    }
}
