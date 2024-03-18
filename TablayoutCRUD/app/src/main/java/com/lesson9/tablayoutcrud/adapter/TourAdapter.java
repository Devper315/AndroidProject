package com.lesson9.tablayoutcrud.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.lesson9.tablayoutcrud.MainActivity;
import com.lesson9.tablayoutcrud.R;
import com.lesson9.tablayoutcrud.fragment.FragmentSearch;
import com.lesson9.tablayoutcrud.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {
    private List<Tour> tourList;
    private MainActivity mainActivity;
    private TourItemListener tourItemListener;

    public TourAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.tourList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item, parent, false);
        return new TourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tour tour = tourList.get(position);
        holder.img.setImageResource(tour.getImg());
        holder.name.setText(tour.getName());
        holder.time.setText(tour.getTime());
        holder.price.setText(tour.getPrice() + "");
        holder.btnRemove.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn xóa tour " + tour.getName() + " không ?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Có", (dialogInterface, i) -> {
                tourList.remove(position);
                notifyDataSetChanged();
                mainActivity.setTourList(tourList);
                FragmentSearch.tourList = tourList;
            });
            builder.setNegativeButton("Không", (dialogInterface, i) -> {

            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public Tour getItem(int position){
        return tourList.get(position);
    }
    public void setTourItemListener(TourItemListener tourItemListener) {
        this.tourItemListener = tourItemListener;
    }

    public List<Tour> getTourList() {
        return tourList;
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView name, time, price;
        private Button btnRemove;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tour_item_image);
            name = itemView.findViewById(R.id.tour_name);
            time = itemView.findViewById(R.id.tour_time);
            price = itemView.findViewById(R.id.tour_price);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            btnRemove.setOnClickListener(TourViewHolder.this);
        }

        @Override
        public void onClick(View view) {
            tourItemListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface TourItemListener{
        void onItemClick(View view, int position);
    }

    public void add (Tour tour){
        tourList.add(tour);
        notifyDataSetChanged();
        mainActivity.setTourList(tourList);
        FragmentSearch.tourList = tourList;
    }

    public void update (Tour tour, int position){
        tourList.set(position, tour);
        notifyDataSetChanged();
        mainActivity.setTourList(tourList);
        FragmentSearch.tourList = tourList;
    }
}
