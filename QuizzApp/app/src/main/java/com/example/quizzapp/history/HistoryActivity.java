package com.example.quizzapp.history;

import static com.example.quizzapp.Utils.loginUser;
import static com.example.quizzapp.Utils.reference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.quizzapp.R;
import com.example.quizzapp.dao.UserAnswerHelper;
import com.example.quizzapp.dao.ResultHelper;
import com.example.quizzapp.model.Result;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    HistoryAdapter adapter;
    DatabaseReference scoreReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        scoreReference = reference.child("score");
        ResultHelper resultHelper = new ResultHelper(this);
        UserAnswerHelper doneHelper = new UserAnswerHelper(this);
        List<Result> resultList = resultHelper.getResultByUserId(loginUser.getUid());
        adapter = new HistoryAdapter(resultList, HistoryActivity.this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        progressBar.setVisibility(View.GONE);

    }
}