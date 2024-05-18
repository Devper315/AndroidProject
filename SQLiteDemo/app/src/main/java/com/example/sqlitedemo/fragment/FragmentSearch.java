package com.example.sqlitedemo.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlitedemo.AddActivity;
import com.example.sqlitedemo.R;
import com.example.sqlitedemo.Utils;
import com.example.sqlitedemo.adapter.RecyclerViewAdapter;
import com.example.sqlitedemo.dao.ItemHelper;
import com.example.sqlitedemo.model.Item;

import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView tvSum;
    private Button btnSearch;
    private SearchView searchView;
    private EditText eFrom, eTo;
    private Spinner spCategory;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ItemHelper itemHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        recyclerViewAdapter = new RecyclerViewAdapter();
        itemHelper = new ItemHelper(getContext());
        List<Item> allItem = itemHelper.getAll();
        recyclerViewAdapter.setItemList(allItem);
        tvSum.setText("Tổng tiền: " + Utils.getSumPrice(allItem) + "VNĐ");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> searchList = itemHelper.getByTitle(s);
                tvSum.setText("Tổng tiền: " + Utils.getSumPrice(searchList));
                recyclerViewAdapter.setItemList(searchList);
                return true;
            }
        });
        eFrom.setOnClickListener(this);
        eTo.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String category = spCategory.getItemAtPosition(position).toString();
                List<Item> searchList;
                if (!category.equalsIgnoreCase("all")) searchList = itemHelper.getByCategory(category);
                else searchList = itemHelper.getAll();
                recyclerViewAdapter.setItemList(searchList);
                tvSum.setText("Tổng tiền: " + Utils.getSumPrice(searchList));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        tvSum = view.findViewById(R.id.tvSum);
        btnSearch = view.findViewById(R.id.btnSearch);
        searchView = view.findViewById(R.id.search);
        eFrom = view.findViewById(R.id.eFrom);
        eTo = view.findViewById(R.id.eTo);
        spCategory = view.findViewById(R.id.spCategory);
        String[] categories = getResources().getStringArray(R.array.category);
        String[] newCategories = new String[categories.length + 1];
        newCategories[0] = "Tất cả";
        for (int i = 0; i < categories.length; i++){
            newCategories[i + 1] = categories[i];
        }
        spCategory.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.category_spinner, newCategories));
    }

    @Override
    public void onClick(View view) {
        if (view == eFrom || view == eTo){
            DatePickerDialog dialog = Utils.buildDateDialog(getContext(), (EditText) view);
            dialog.show();
        }
        if (view == btnSearch){
            String from = eFrom.getText().toString();
            String to = eTo.getText().toString();
            if (!from.isEmpty() && !to.isEmpty()){
                List<Item> searchList = itemHelper.getByTimeRange(from, to);
                recyclerViewAdapter.setItemList(searchList);
                tvSum.setText("Tổng tiền: " + Utils.getSumPrice(searchList));
            }
        }

    }
}
