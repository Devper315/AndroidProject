package com.lesson3.layoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class TimeActivity extends AppCompatActivity {
    private CheckBox ck1, ck2, ck3;
    private RadioButton male, female;
    private RatingBar rating;
    private Spinner sp1;
    private TextView res;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.excersise_some_widget);
        initView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ss = "Check box: ";
                if (ck1.isChecked()){
                    ss += ck1.getText() + ", ";
                }
                if (ck2.isChecked()){
                    ss += ck2.getText() + ", ";
                }
                if (ck3.isChecked()){
                    ss += ck3.getText() + ", ";
                }
                if (ss.endsWith(", ")) ss = ss.substring(0, ss.length() - 2);
                ss += "\nGiới tính: ";
                if (male.isChecked()){
                    ss += male.getText();
                }
                if (female.isChecked()){
                    ss += female.getText();
                }
                ss += "\nRating:" + rating.getRating();
                ss += "\n" + sp1.getSelectedItem().toString();
                res.setText(ss);
            }
        });
    }

    private void initView(){
        ck1 = findViewById(R.id.ck1);
        ck2 = findViewById(R.id.ck2);
        ck3 = findViewById(R.id.ck3);
        rating = findViewById(R.id.rating);
        sp1 = findViewById(R.id.sp1);
        String[] list = getResources().getStringArray(R.array.country);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, list);
        sp1.setAdapter(adapter);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        btn = findViewById(R.id.btnRes);
        res = findViewById(R.id.result);
    }
}