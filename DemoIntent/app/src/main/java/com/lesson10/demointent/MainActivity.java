package com.lesson10.demointent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(MainActivity.this, SecondActivity.class);
                String name = "Phan Thi";
                t.putExtra("name", name);
                String[] subject = {"Java", "Python"};
                t.putExtra("subject", subject);
                Student student = new Student(R.drawable.car, "Phan Văn Thi", 22);
                t.putExtra("student", student);
                List<Student> studentList = new ArrayList<>();
                studentList.add(student);
                studentList.add(new Student(R.drawable.ic_launcher_background, "Trần Thị Thắm", 22));
                t.putExtra("studentList", (Serializable) studentList);
                startActivity(t);
            }
        });
    }
}