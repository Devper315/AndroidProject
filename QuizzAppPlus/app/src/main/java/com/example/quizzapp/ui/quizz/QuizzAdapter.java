package com.example.quizzapp.ui.quizz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.R;
import com.example.quizzapp.model.Question;

import java.util.List;

public class QuizzAdapter extends RecyclerView.Adapter<QuizzAdapter.QuizzViewHolder> {
    private Context context;
    private List<Question> questionList;

    public QuizzAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuizzViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);
        QuizzViewHolder viewHolder = new QuizzViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QuizzViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.question.setText("Câu " + (position + 1) + ": " + question.getQuestion());
        holder.answer.setText(question.getAnswer());
        holder.option1.setText(question.getOption1());
        holder.option2.setText(question.getOption2());
        holder.option3.setText(question.getOption3());
        holder.option4.setText(question.getOption4());

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }


    public static class QuizzViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer;
        public RadioButton option1, option2, option3, option4;

        public QuizzViewHolder(@NonNull View view) {
            super(view);
            question = view.findViewById(R.id.question);
            answer = view.findViewById(R.id.answer);
            option1 = view.findViewById(R.id.option1);
            option2 = view.findViewById(R.id.option2);
            option3 = view.findViewById(R.id.option3);
            option4 = view.findViewById(R.id.option4);
        }

    }
}
