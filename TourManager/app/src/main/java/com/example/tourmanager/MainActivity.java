package com.example.tourmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tourmanager.model.Tour;
import com.example.tourmanager.model.TourAdapter;
import com.example.tourmanager.model.TourSpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TourAdapter.TourItemListener,
        SearchView.OnQueryTextListener {
    private Spinner vehicleSpinner;
    private RecyclerView recyclerView;
    private TourAdapter tourAdapter;
    private EditText eItinerary, eTime, ePrice;
    private Button btnAdd, btnUpdate;
    private SearchView searchView;
    private int currentPosition;
    private int[] imgs = {R.drawable.car, R.drawable.motorbike};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        tourAdapter = new TourAdapter(this);
        // MainActivity implement TourItemInterface nên truyền this vào
        tourAdapter.setTourClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(tourAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                String vehicleSelected = vehicleSpinner.getSelectedItem().toString();
                String itinerary = eItinerary.getText().toString();
                String time = eTime.getText().toString();
                String priceStr = ePrice.getText().toString();
                int vehicleImg = R.drawable.car;
                double price = 0;
                try {
                    vehicleImg = imgs[Integer.valueOf(vehicleSelected)];
                    price = Double.valueOf(priceStr);
                    tour.setItinerary(itinerary);
                    tour.setPrice(price);
                    tour.setTime(time);
                    tour.setImg(vehicleImg);
                    tourAdapter.add(tour);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                String vehicleSelected = vehicleSpinner.getSelectedItem().toString();
                String itinerary = eItinerary.getText().toString();
                String time = eTime.getText().toString();
                String priceStr = ePrice.getText().toString();
                int vehicleImg = R.drawable.car;
                double price = 0;
                try {
                    vehicleImg = imgs[Integer.valueOf(vehicleSelected)];
                    price = Double.valueOf(priceStr);
                    tour.setItinerary(itinerary);
                    tour.setPrice(price);
                    tour.setTime(time);
                    tour.setImg(vehicleImg);
                    tourAdapter.update(currentPosition, tour);
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                }

            }

        });
        searchView.setOnQueryTextListener(this);

    }

    private void initView() {
        vehicleSpinner = findViewById(R.id.img_spinner);
        TourSpinnerAdapter csa = new TourSpinnerAdapter(this);
        vehicleSpinner.setAdapter(csa);
        recyclerView = findViewById(R.id.recycleView);
        eItinerary = findViewById(R.id.eItinerary);
        eTime = findViewById(R.id.eTime);
        ePrice = findViewById(R.id.ePrice);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.search_tour);
    }


    // Activity implement interface bắt sự kiện, có thêm phương thức onTourItemClick xử lý sự kiện
    // phương thức này lấy ra thông tin của Object hiện tại và hiển thị lên form chỉnh sửa
    @Override
    public void onTourItemClick(View view, int position) {
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        this.currentPosition = position;
        Tour tour = this.tourAdapter.getTourAtPosition(position);
        int currentImg = tour.getImg();
        int imgPosition = 0;
        for (int i = 0; i < this.imgs.length; i++) {
            if (currentImg == imgs[i]) {
                imgPosition = i;
                break;
            }
        }
        this.vehicleSpinner.setSelection(imgPosition);
        this.eItinerary.setText(tour.getItinerary());
        this.eTime.setText(tour.getTime());
        this.ePrice.setText(tour.getPrice() + "");

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (s.length() == 0) {
            tourAdapter.filterList(tourAdapter.getToursBackup());
        } else {
            List<Tour> filterTours = new ArrayList<>();
            for (Tour t : tourAdapter.getToursBackup()) {
                if (t.getItinerary().toLowerCase().contains(s.toLowerCase())) {
                    filterTours.add(t);
                }
            }
            if (filterTours.isEmpty()) {
                Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
            } else
                tourAdapter.filterList(filterTours);
        }

        return true;
    }
}
