package com.lesson5.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private List<Tour> tourList;
    private Context context;
    private TourItemListener tourItemListener;


    public TourAdapter(Context context, List<Tour> tourList) {
        this.context = context;
        this.tourList = tourList;
    }

    public void setTourItemListener(TourItemListener tourItemListener) {
        this.tourItemListener = tourItemListener;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item, parent, false);
        TourViewHolder viewHolder = new TourViewHolder(view);
//        view.setOnClickListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.img.setImageResource(tour.getImg());
        holder.name.setText(tour.getName());

    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }


    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView name;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tour_item_img);
            name = itemView.findViewById(R.id.tour_item_name);
        }

        @Override
        public void onClick(View view) {
            tourItemListener.onItemClick(view, getAdapterPosition());
        }
    }

}
