package com.example.quizzapp.quizz;

import static com.example.quizzapp.Utils.reference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.quizzapp.R;
import com.example.quizzapp.Utils;
import com.example.quizzapp.adapter.QuizzAdapter;
import com.example.quizzapp.model.Question;
import com.example.quizzapp.model.QuestionDone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StartQuizz extends AppCompatActivity {
    RecyclerView recyclerView;
    QuizzAdapter quizzAdapter;
    Button submitBtn;
    int score = 0;
    List<QuestionDone> doneList = new ArrayList<>();


    private List<Question> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quizz);
        Utils.getDatabaseReference();
        reference.child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String question = snapshot.child("question").getValue().toString();
                    String option1 = snapshot.child("A").getValue().toString();
                    String option2 = snapshot.child("B").getValue().toString();
                    String option3 = snapshot.child("C").getValue().toString();
                    String option4 = snapshot.child("D").getValue().toString();
                    String answer = snapshot.child("correct_answer").getValue().toString();
                    questionList.add(new Question(option1, option2, option3, option4, question, answer));
                }
                recyclerView = findViewById(R.id.recycler_view);
                quizzAdapter = new QuizzAdapter(StartQuizz.this, questionList);
                recyclerView.setAdapter(quizzAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(StartQuizz.this);
                recyclerView.setLayoutManager(layoutManager);
                submitBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(view -> {
            saveTheTest();
        });
    }

    private void saveTheTest(){
        boolean done = true;
        for(int i = 0; i < questionList.size(); i++){
            View view = recyclerView.getChildAt(i);
            QuizzAdapter.QuizzViewHolder viewHolder = new QuizzAdapter.QuizzViewHolder(view);
            QuestionDone newDone = new QuestionDone(viewHolder);
            if (newDone.getSelected() == null) {
                done = false;
                Toast.makeText(this, "Chưa đủ", Toast.LENGTH_LONG).show();
                doneList.clear();
                break;
            }
            if (newDone.markQuestion()) score += 1;
            doneList.add(newDone);
        }
        if (done){
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("doneList", (Serializable) doneList);
            startActivity(intent);
            finish();
        }
    }
}