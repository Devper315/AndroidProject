package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class DataTimeActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eDate, eTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_time);
        eDate = findViewById(R.id.eDate);
        eTime = findViewById(R.id.eTime);
        eDate.setOnClickListener(this);
        eTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Calendar c = Calendar.getInstance();
        if (view == eTime){
            int currentHour = c.get(Calendar.HOUR_OF_DAY);
            int currentMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                    eTime.setText(hour + ": " + minute);
                }
            }, currentHour, currentMinute, false);
            timeDialog.show();
        }
        if (view == eDate){
            int currentYear = c.get(Calendar.YEAR);
            int currentMonth = c.get(Calendar.MONTH);
            int currentDate = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                    eDate.setText(date + "/" + month + "/" + year);
                }
            }, currentYear, currentMonth, currentDate);
            dateDialog.show();
        }
    }
}