package com.example.quizzapp.history;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.R;
import com.example.quizzapp.dao.QuestionDoneHelper;
import com.example.quizzapp.dao.QuizzHelper;
import com.example.quizzapp.dao.ResultHelper;
import com.example.quizzapp.model.QuestionDone;
import com.example.quizzapp.model.Result;

import java.io.Serializable;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ScoreViewHolder>{
    private List<Result> resultList;
    private Context context;

    public HistoryAdapter(List<Result> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        ScoreViewHolder viewHolder = new ScoreViewHolder(view);
        view.setOnClickListener(viewHolder);
        return viewHolder;
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.score.setText("Kết quả: " + result.getScore());
        holder.datetime.setText(result.getDatetime());
        holder.deleteBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa kết quả này?");
            builder.setIcon(R.drawable.icon_delete);
            builder.setPositiveButton("Có", (dialogInterface, i) -> {
                ResultHelper resultHelper = new ResultHelper(context);
                QuestionDoneHelper doneHelper = new QuestionDoneHelper(context);
                doneHelper.deleteByResultId(result.getId());
                resultHelper.deleteById(result.getId());
                resultList.remove(result);
                notifyDataSetChanged();
            });
            builder.setNegativeButton("Không", (dialogInterface, i) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView score, datetime;
        Button deleteBtn;
        public ScoreViewHolder(@NonNull View view) {
            super(view);
            score = view.findViewById(R.id.score);
            datetime = view.findViewById(R.id.datetime);
            deleteBtn = view.findViewById(R.id.delete_btn);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, QuestionDoneActivity.class);
            int position = getAdapterPosition();
            intent.putExtra("result", resultList.get(position));
            context.startActivity(intent);
        }
    }
}
