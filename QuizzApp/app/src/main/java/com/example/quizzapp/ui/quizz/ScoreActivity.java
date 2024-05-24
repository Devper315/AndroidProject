package com.example.quizzapp.ui.quizz;

import static com.example.quizzapp.Utils.loginUser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.R;
import com.example.quizzapp.dao.UserAnswerHelper;
import com.example.quizzapp.dao.ResultHelper;
import com.example.quizzapp.history.HistoryActivity;
import com.example.quizzapp.model.UserAnwser;
import com.example.quizzapp.model.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTxt, totalTxt;
    int score, total;
    Button historyBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 0);
        scoreTxt = findViewById(R.id.score);
        totalTxt = findViewById(R.id.total);
        historyBtn = findViewById(R.id.history_btn);
        scoreTxt.setText(score + "");
        totalTxt.setText(total + "");
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDate);
        Result result = new Result(score + "/" + total, loginUser.getUid(), formattedDateTime);
        ResultHelper resultHelper = new ResultHelper(this);
        List<UserAnwser> doneList = (List<UserAnwser>) getIntent().getSerializableExtra("doneList");
        Long newResultId = resultHelper.addResult(result);
        UserAnswerHelper doneHelper = new UserAnswerHelper(this);
        doneHelper.addDoneList(doneList, newResultId);
        historyBtn.setOnClickListener(view ->{
            Intent intent = new Intent(ScoreActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
}