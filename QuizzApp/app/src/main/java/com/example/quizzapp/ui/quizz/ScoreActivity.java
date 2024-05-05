package com.example.quizzapp.ui.quizz;

import static com.example.quizzapp.Utils.loginUser;
import static com.example.quizzapp.Utils.reference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.R;
import com.example.quizzapp.Utils;
import com.example.quizzapp.dao.QuizzHelper;
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

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTxt, totalTxt;
    int score, total;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 0);
        scoreTxt = findViewById(R.id.score);
        totalTxt = findViewById(R.id.total);
        scoreTxt.setText(score + "");
        totalTxt.setText(score + "");
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = dateFormat.format(currentDate);
        Result result = new Result(score, loginUser.getUid(), formattedDateTime);
        QuizzHelper helper = new QuizzHelper(this);
        helper.addResult(result);

    }
}