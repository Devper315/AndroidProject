package com.example.sqlitedemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.ModifyActivity;
import com.example.sqlitedemo.adapter.RecyclerViewAdapter;
import com.example.sqlitedemo.dao.ItemHelper;
import com.example.sqlitedemo.model.Item;
import com.example.sqlitedemo.model.ItemListener;

import java.util.List;

public class FragmentHistory extends Fragment implements ItemListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ItemHelper itemHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter();
        itemHelper = new ItemHelper(getContext());
        List<Item> itemList = itemHelper.getAll();
        adapter.setItemList(itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setItemListener(this);

    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItemList().get(position);
        Intent intent = new Intent(getActivity(), ModifyActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> itemList = itemHelper.getAll();
        adapter.setItemList(itemList);
        adapter.notifyDataSetChanged();
    }
}
