package com.example.sqlitedemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.Utils;
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
        Utils.updateItemListShow(adapter, itemList);

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
