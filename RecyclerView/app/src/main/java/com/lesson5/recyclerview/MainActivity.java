package com.lesson5.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TourItemListener{
    private RecyclerView recyclerView;
    private List<Tour> tourList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.tourRecyclerView);
        this.tourList = initData();
        TourAdapter adapter = new TourAdapter(MainActivity.this, tourList);
        adapter.setTourItemListener(MainActivity.this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        
    }

    private List<Tour> initData(){
        List<Tour> tourList = new ArrayList<>();
        tourList.add(new Tour(R.drawable.car, "Tour by car"));
        tourList.add(new Tour(R.drawable.plane, "Tour by plane"));
        tourList.add(new Tour(R.drawable.motorbike, "Tour by motorbike"));
        return tourList;
    }

    @Override
    public void onItemClick(View view, int position) {
        Tour tour = tourList.get(position);
        Toast.makeText(this, tour.getName(), Toast.LENGTH_LONG).show();
    }
}