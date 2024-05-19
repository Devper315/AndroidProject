package com.example.quizzapp.quizz;

import static com.example.quizzapp.Utils.loginUser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzapp.R;
import com.example.quizzapp.helper.QuestionDoneHelper;
import com.example.quizzapp.helper.ResultHelper;
import com.example.quizzapp.history.QuestionDoneActivity;
import com.example.quizzapp.model.QuestionDone;
import com.example.quizzapp.model.Result;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTxt, totalTxt;
    int score;
    Button historyBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score = getIntent().getIntExtra("score", 0);
        scoreTxt = findViewById(R.id.score);
        totalTxt = findViewById(R.id.total);
        historyBtn = findViewById(R.id.history_btn);
        scoreTxt.setText(score + "");
        Date currentDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDate);
        List<QuestionDone> doneList = (List<QuestionDone>) getIntent().getSerializableExtra("doneList");
        Result result = new Result(score + "/" + doneList.size(), loginUser.getUid(), formattedDateTime);
        totalTxt.setText(doneList.size() + "");
        // khởi tạo kết nối DB
        ResultHelper resultHelper = new ResultHelper(this);
        QuestionDoneHelper doneHelper = new QuestionDoneHelper(this);
        Long newResultId = resultHelper.addResult(result);
        doneHelper.addDoneList(doneList, newResultId);
        historyBtn.setOnClickListener(view -> {
            Intent intent = new Intent(ScoreActivity.this, QuestionDoneActivity.class);
            intent.putExtra("result", result);
            intent.putExtra("newResultId", Integer.valueOf(String.valueOf(newResultId)));
            startActivity(intent);
        });
    }
}