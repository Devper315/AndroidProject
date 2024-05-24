package com.example.quizzapp.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizzapp.R;
import com.example.quizzapp.dao.UserAnswerHelper;
import com.example.quizzapp.model.UserAnwser;
import com.example.quizzapp.model.Result;

import java.util.List;

public class UserAnswerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_answer);
        Result result = (Result) getIntent().getSerializableExtra("result");
        UserAnswerHelper doneHelper = new UserAnswerHelper(this);
        List<UserAnwser> doneList = doneHelper.getDoneListByResult(result);
        recyclerView = findViewById(R.id.recycler_view);
        UserAnswerAdapter doneAdapter = new UserAnswerAdapter(this, doneList);
        recyclerView.setAdapter(doneAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}