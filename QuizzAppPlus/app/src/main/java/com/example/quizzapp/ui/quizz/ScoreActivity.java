package com.example.quizzapp.ui.quizz;

import static com.example.quizzapp.Utils.loginUser;
import static com.example.quizzapp.Utils.reference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.R;
import com.example.quizzapp.Utils;
import com.example.quizzapp.dao.QuestionDoneHelper;
import com.example.quizzapp.dao.QuizzHelper;
import com.example.quizzapp.dao.ResultHelper;
import com.example.quizzapp.history.HistoryFragment;
import com.example.quizzapp.model.QuestionDone;
import com.example.quizzapp.model.Result;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
        NavController navController = Navigation.findNavController(this, R.id.main_fragment);
        historyBtn.setOnClickListener(view ->{
        });
    }
}