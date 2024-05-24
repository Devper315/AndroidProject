package com.example.quizzapp.history;

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
import com.example.quizzapp.model.UserAnwser;

import java.util.List;

public class UserAnswerAdapter extends RecyclerView.Adapter<UserAnswerAdapter.UserAnswerViewHolder> {
    private Context context;
    private List<UserAnwser> doneList;

    public UserAnswerAdapter(Context context, List<UserAnwser> doneList) {
        this.context = context;
        this.doneList = doneList;
    }

    @NonNull
    @Override
    public UserAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_answer_item, parent, false);
        UserAnswerViewHolder viewHolder = new UserAnswerViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserAnswerViewHolder holder, int position) {
        UserAnwser userAnwser = doneList.get(position);
        holder.question.setText("Câu " + (position + 1) + ": " + userAnwser.getQuestion());
        holder.option1.setText(userAnwser.getOption1());
        holder.showAnswer(holder.option1, userAnwser.getSelected(), userAnwser.getAnswer());
        holder.option2.setText(userAnwser.getOption2());
        holder.showAnswer(holder.option2, userAnwser.getSelected(), userAnwser.getAnswer());
        holder.option3.setText(userAnwser.getOption3());
        holder.showAnswer(holder.option3, userAnwser.getSelected(), userAnwser.getAnswer());
        holder.option4.setText(userAnwser.getOption4());
        holder.showAnswer(holder.option4, userAnwser.getSelected(), userAnwser.getAnswer());

    }

    @Override
    public int getItemCount() {
        return doneList.size();
    }

    public class UserAnswerViewHolder extends RecyclerView.ViewHolder {
        TextView question;
        RadioButton option1, option2, option3, option4;

        public UserAnswerViewHolder(@NonNull View view) {
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
