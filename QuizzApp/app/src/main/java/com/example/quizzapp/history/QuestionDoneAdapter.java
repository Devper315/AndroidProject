package com.example.quizzapp.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.R;
import com.example.quizzapp.model.QuestionDone;

import java.util.List;

public class QuestionDoneAdapter extends RecyclerView.Adapter<QuestionDoneAdapter.QuestionDoneViewHolder> {
    private Context context;
    private List<QuestionDone> doneList;

    public QuestionDoneAdapter(Context context, List<QuestionDone> doneList) {
        this.context = context;
        this.doneList = doneList;
    }

    @NonNull
    @Override
    public QuestionDoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_done_item, parent, false);
        QuestionDoneViewHolder viewHolder = new QuestionDoneViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionDoneViewHolder holder, int position) {
        QuestionDone questionDone = doneList.get(position);
        holder.question.setText(questionDone.getQuestion());
        holder.option1.setText(questionDone.getOption1());
        holder.option2.setText(questionDone.getOption2());
        holder.option3.setText(questionDone.getOption3());
        holder.option4.setText(questionDone.getOption4());
    }

    @Override
    public int getItemCount() {
        return doneList.size();
    }

    public class QuestionDoneViewHolder extends RecyclerView.ViewHolder{
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
    }
}
