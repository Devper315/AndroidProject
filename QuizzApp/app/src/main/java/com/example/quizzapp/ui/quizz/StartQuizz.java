package com.example.quizzapp.ui.quizz;

import static com.example.quizzapp.Utils.reference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzapp.R;
import com.example.quizzapp.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StartQuizz extends AppCompatActivity {
    private TextView questionTxt, indicator;
    private LinearLayout container;
    private Button nextBtn, shareBtn;
    private int score = 0;
    private int position = 0;
    private int count = 0;
    private List<QuestionData> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quizz);
        questionTxt = findViewById(R.id.question);
        indicator = findViewById(R.id.indicator);
        container = findViewById(R.id.container);
        nextBtn = findViewById(R.id.next_btn);
        shareBtn = findViewById(R.id.share_btn);
        questionList = new ArrayList<>();
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
                    questionList.add(new QuestionData(option1, option2, option3, option4, question, answer));
                }
                if (questionList.size() > 0) {
                    loadQuestion(questionTxt, 0, questionList.get(position).getQuestion());

                    for (int i = 0; i < 4; i++) {
                        container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkAnswer((Button) view);
                            }
                        });
                    }
                    nextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            nextBtn.setEnabled(false);
                            nextBtn.setAlpha(0.7f);
                            enabled(true);
                            position++;
                            if (position == questionList.size()){
                                Intent intent = new Intent(StartQuizz.this, ScoreActivity.class);
                                intent.putExtra("score", score);
                                intent.putExtra("total", questionList.size());
                                startActivity(intent);
                                finish();
                                return;

                            }
                            count = 0;
                            loadQuestion(questionTxt, 0, questionList.get(position).getQuestion());
                        }
                    });
                    shareBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            QuestionData question = questionList.get(position);
                            String body = "*" + question.getQuestion() + "*\n" +
                                    "(a) " + question.getOption1() + "\n" +
                                    "(b) " + question.getOption2() + "\n" +
                                    "(c) " + question.getOption3() + "\n" +
                                    "(d) " + question.getOption4();
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("Text/Plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Learning App");
                            intent.putExtra(Intent.EXTRA_TEXT, body);
                            startActivity(Intent.createChooser(intent, "Share via"));
                        }
                    });
                }
                else {
                    Toast.makeText(StartQuizz.this, "Không có câu hỏi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StartQuizz.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkAnswer(Button selectedOption) {
        enabled(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        String answer = questionList.get(position).getAnswer();
        if (selectedOption.getText().toString().equals(answer)) {
            score++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4caf50")));
        } else {
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            Button correctOption = container.findViewWithTag(answer);
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4caf50")));
        }
    }

    private void enabled(Boolean enable) {
        for (int i = 0; i < 4; i++) {
            container.getChildAt(i).setEnabled(enable);
            if (enable) {
                container.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }
    }

    private void loadQuestion(View view, int value, String data) {
        for (int i = 0; i < 4; i++) {
            container.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
        }
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                        if (value == 0 && count < 4) {
                            String option = "";
                            if (count == 0) option = questionList.get(position).getOption1();
                            if (count == 1) option = questionList.get(position).getOption2();
                            if (count == 2) option = questionList.get(position).getOption3();
                            if (count == 3) option = questionList.get(position).getOption4();
                            loadQuestion(container.getChildAt(count), 0, option);
                            count++;
                        }
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        if (value == 0) {
                            ((TextView) view).setText(data);
                            indicator.setText(position + "/" + questionList.size());
                        }
                        view.setTag(data);
                        loadQuestion(view, 1, data);
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {

                    }
                });
    }
}