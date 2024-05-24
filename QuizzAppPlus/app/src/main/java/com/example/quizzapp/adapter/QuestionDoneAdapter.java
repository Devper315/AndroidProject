package com.example.quizzapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.R;
import com.example.quizzapp.model.UserAnswer;

import java.util.List;

public class QuestionDoneAdapter extends RecyclerView.Adapter<QuestionDoneAdapter.QuestionDoneViewHolder> {
    private Context context;
    private List<UserAnswer> doneList;

    public QuestionDoneAdapter(Context context, List<UserAnswer> doneList) {
        this.context = context;
        this.doneList = doneList;
    }

    @NonNull
    @Override
    public QuestionDoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_answer_item, parent, false);
        QuestionDoneViewHolder viewHolder = new QuestionDoneViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull QuestionDoneViewHolder holder, int position) {
        UserAnswer userAnswer = doneList.get(position);
        holder.question.setText("Câu " + (position + 1) + ": " + userAnswer.getQuestion());
        holder.option1.setText(userAnswer.getOption1());
        holder.showAnswer(holder.option1, userAnswer.getSelected(), userAnswer.getAnswer());
        holder.option2.setText(userAnswer.getOption2());
        holder.showAnswer(holder.option2, userAnswer.getSelected(), userAnswer.getAnswer());
        holder.option3.setText(userAnswer.getOption3());
        holder.showAnswer(holder.option3, userAnswer.getSelected(), userAnswer.getAnswer());
        holder.option4.setText(userAnswer.getOption4());
        holder.showAnswer(holder.option4, userAnswer.getSelected(), userAnswer.getAnswer());

    }

    @Override
    public int getItemCount() {
        return doneList.size();
    }

    public class QuestionDoneViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioButton option1, option2, option3, option4;

        public QuestionDoneViewHolder(@NonNull View view) {
            super(view);
            question = view.findViewById(R.id.question);
            option1 = view.findViewById(R.id.option1);
            option2 = view.findViewById(R.id.option2);
            option3 = view.findViewById(R.id.option3);
            option4 = view.findViewById(R.id.option4);
        }

        public void showAnswer(RadioButton button, String selected, String answer) {
            String buttonContent = button.getText().toString();
            if (buttonContent.equals(answer))
                button.setTextColor(Color.parseColor("#4caf50"));
            if (buttonContent.equals(selected)) {
                button.setChecked(true);
                if (!buttonContent.equals(answer))
                    button.setTextColor(Color.parseColor("#ff0000"));
            }
        }
    }
}
