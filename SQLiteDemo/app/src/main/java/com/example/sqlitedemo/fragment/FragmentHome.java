package com.example.sqlitedemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.R;
import com.example.sqlitedemo.ModifyActivity;
import com.example.sqlitedemo.Utils;
import com.example.sqlitedemo.adapter.RecyclerViewAdapter;
import com.example.sqlitedemo.dao.ItemHelper;
import com.example.sqlitedemo.model.Item;
import com.example.sqlitedemo.model.ItemListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentHome extends Fragment implements ItemListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ItemHelper itemHelper;
    private TextView tvSum;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvSum = view.findViewById(R.id.tvSum);
        adapter = new RecyclerViewAdapter();
        itemHelper = new ItemHelper(getContext());
        showTodayList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);


    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItemList().get(position);
        Intent intent = new Intent(getActivity(), ModifyActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }


    private void showTodayList(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Item> todayList = itemHelper.getByDate(sdf.format(date));
        adapter.setItemList(todayList);
        tvSum.setText("Tổng tiền: " + Utils.getSumPrice(todayList));
    }

    @Override
    public void onResume() {
        super.onResume();
        showTodayList();
    }
}
