package com.example.songmanager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

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
}
