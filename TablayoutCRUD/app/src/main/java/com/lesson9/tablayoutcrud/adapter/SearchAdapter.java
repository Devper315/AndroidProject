package com.lesson9.tablayoutcrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lesson9.tablayoutcrud.R;
import com.lesson9.tablayoutcrud.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private List<Tour> tourListSearch;

    public SearchAdapter() {
        this.tourListSearch = new ArrayList<>();
    }

    public List<Tour> getTourListSearch() {
        return tourListSearch;
    }

    public void setTourListSearch(List<Tour> tourListSearch) {
        this.tourListSearch = tourListSearch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_item_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Tour tour = tourListSearch.get(position);
        holder.image.setImageResource(tour.getImg());
        holder.name.setText(tour.getName());
        holder.price.setText(tour.getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return tourListSearch.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name, time, price;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.tour_item_image);
            name = itemView.findViewById(R.id.tour_name);
            time = itemView.findViewById(R.id.tour_time);
            price = itemView.findViewById(R.id.tour_price);
        }
    }
}
