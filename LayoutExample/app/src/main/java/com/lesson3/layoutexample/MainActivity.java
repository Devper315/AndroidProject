package com.lesson3.layoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner sp1, sp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.excersise_some_widget);
        sp2 = findViewById(R.id.sp2);
        String[] list2 = {"PTIT", "HUST", "NEU", "FTU"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.spinner_item, list2);
        sp2.setAdapter(adapter2);
        String[] list1 = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.spinner_item, list1);
        sp1 = findViewById(R.id.sp1);
        sp1.setAdapter(adapter1);
    }
}