package com.example.quizzapp.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizzapp.R;
import com.example.quizzapp.dao.QuestionDoneHelper;
import com.example.quizzapp.model.QuestionDone;
import com.example.quizzapp.model.Result;

import java.util.List;

public class QuestionDoneActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_done);
        Result result = (Result) getIntent().getSerializableExtra("result");
        QuestionDoneHelper doneHelper = new QuestionDoneHelper(this);
        List<QuestionDone> doneList = doneHelper.getDoneListByResult(result);
        recyclerView = findViewById(R.id.recycler_view);
        QuestionDoneAdapter doneAdapter = new QuestionDoneAdapter(this, doneList);
        recyclerView.setAdapter(doneAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}