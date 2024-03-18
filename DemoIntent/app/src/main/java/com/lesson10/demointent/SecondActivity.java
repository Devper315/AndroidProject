package com.lesson10.demointent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Button btn;
    private TextView tvName, tvSubject, studentInfo, tvList;
    private ImageView imageView;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn = findViewById(R.id.btn);
        initView();
        Intent t = getIntent();
        tvName.setText(t.getStringExtra("name"));
        String[] subjectReceive = t.getStringArrayExtra("subject");
        tvSubject.setText(Arrays.toString(subjectReceive));
        Student studentReceive = (Student) t.getSerializableExtra("student");
        imageView.setImageResource(studentReceive.getImg());
        studentInfo.setText(studentReceive.getName() + " " + studentReceive.getAge());
        List<Student> studentList = (List<Student>) t.getSerializableExtra("studentList");
        String infoList = "";
        for(Student s: studentList){
            infoList += s.getName() + " " + s.getAge() + "\n";
        }
        studentInfo.setText(infoList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initView(){
        tvName = findViewById(R.id.tvName);
        tvSubject = findViewById(R.id.tvSubject);
        studentInfo = findViewById(R.id.studentInfo);
        imageView = findViewById(R.id.img);
        tvList = findViewById(R.id.tvList);
    }
}