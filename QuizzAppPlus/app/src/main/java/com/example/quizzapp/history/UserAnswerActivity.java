package com.example.quizzapp.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizzapp.R;
import com.example.quizzapp.adapter.QuestionDoneAdapter;
import com.example.quizzapp.helper.UserAnswerHelper;
import com.example.quizzapp.model.UserAnswer;
import com.example.quizzapp.model.Result;

import java.util.List;

public class UserAnswerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tvResult;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_answer);
        Result result = (Result) getIntent().getSerializableExtra("result");
        UserAnswerHelper doneHelper = new UserAnswerHelper(this);
        List<UserAnswer> doneList;
        if (result.getId() != 0){
            doneList = doneHelper.getDoneListByResultId(result.getId());
        }
        else doneList = doneHelper.getDoneListByResultId(getIntent().getIntExtra("newResultId", 0));
        recyclerView = findViewById(R.id.recycler_view);
        tvResult = findViewById(R.id.result);
        tvResult.setText("Số câu đúng: " + result.getScore());
        QuestionDoneAdapter doneAdapter = new QuestionDoneAdapter(this, doneList);
        recyclerView.setAdapter(doneAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}