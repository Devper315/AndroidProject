package com.example.sqlitedemo.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.model.Item;
import com.example.sqlitedemo.model.ItemListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HomeViewHolder> {

    private ItemListener itemListener;
    private List<Item> itemList;

    public RecyclerViewAdapter() {
        this.itemList = new ArrayList<>();
    }



    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(view);
        view.setOnClickListener(homeViewHolder);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.title.setText(item.getTitle());
        holder.category.setText(item.getCategory());
        holder.price.setText(item.getPrice() + "");
        holder.date.setText(item.getDate());
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title, category, price, date;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.tvTitle);
            category = view.findViewById(R.id.tvCategory);
            price = view.findViewById(R.id.tvPrice);
            date = view.findViewById(R.id.tvDate);
        }

        @Override
        public void onClick(View view) {
            itemListener.onItemClick(view, getAdapterPosition());
        }
    }
}
