package com.example.quizzapp.leaderboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizzapp.R;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>{
    private List<ScoreData> scoreList;
    private Context context;
    private int rank = 1;

    public ScoreAdapter(List<ScoreData> scoreList, Context context) {
        this.scoreList = scoreList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.score_list_item, parent, false);
        return new ScoreViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        ScoreData score = scoreList.get(position);
        holder.name.setText(score.getName());
        holder.score.setText(score.getScore() + "");
        holder.rank.setText(rank + "");
        Glide.with(context).load(score.getImage()).into(holder.imageView);
        rank++;
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name, score, rank;
        public ScoreViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.user_image);
            name = view.findViewById(R.id.user_name);
            score = view.findViewById(R.id.user_result);
            rank = view.findViewById(R.id.user_rank);
        }
    }
}
