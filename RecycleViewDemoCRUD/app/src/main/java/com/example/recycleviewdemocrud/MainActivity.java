package com.example.recycleviewdemocrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recycleviewdemocrud.listener.CatItemListener;
import com.example.recycleviewdemocrud.model.Tour;
import com.example.recycleviewdemocrud.model.TourAdapter;
import com.example.recycleviewdemocrud.model.SpinnerAdapter;
import com.example.recycleviewdemocrud.utils.Utils;


public class MainActivity extends AppCompatActivity implements CatItemListener,
        SearchView.OnQueryTextListener {
    private Spinner sp;
    private RecyclerView recyclerView;
    private TourAdapter tourAdapter;
    private EditText eName, eDesc;
    private Button btAdd, btUpdate;
    private SearchView searchView;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        tourAdapter = new TourAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(tourAdapter);
        tourAdapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btAdd.setOnClickListener(view -> {
            handleOnClickButton("add");
        });
        btUpdate.setOnClickListener(view -> {
            handleOnClickButton("update");
        });
    }

    private void initView() {
        sp = findViewById(R.id.img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView = findViewById(R.id.recycleView);
        eName = findViewById(R.id.name);
        eDesc = findViewById(R.id.describe);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
    }

    private void handleOnClickButton(String type) {
        String i = sp.getSelectedItem().toString();
        String name = eName.getText().toString();
        String desc = eDesc.getText().toString();
        int img = Utils.imgs[Integer.parseInt(i)];
        if (name.length() != 0 && desc.length() != 0) {
            Tour newTour = new Tour(img, name, desc);
            if (type.equals("add"))
                Utils.addTour(newTour, tourAdapter);
            if (type.equals("update"))
                Utils.updateTourList(newTour, currentPosition, tourAdapter);
        } else {
            String message = "Thông tin lịch trình hoặc thời gian không thể trống";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        currentPosition = position;
        Tour tour = tourAdapter.getItem(position);
        sp.setSelection(tour.getImg());
        eName.setText(tour.getPath());
        eDesc.setText(tour.getTime());
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Utils.searchTour(s, tourAdapter);
        return true;
    }

}