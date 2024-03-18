package com.lesson9.tablayoutcrud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesson9.tablayoutcrud.MainActivity;
import com.lesson9.tablayoutcrud.R;
import com.lesson9.tablayoutcrud.adapter.SpinnerAdapter;
import com.lesson9.tablayoutcrud.adapter.TourAdapter;
import com.lesson9.tablayoutcrud.model.Tour;

public class FragmentAdd extends Fragment implements TourAdapter.TourItemListener {
    private TourAdapter tourAdapter;
    private Spinner spinner;
    private EditText eName, eTime, ePrice;
    private Button btnAdd, btnUpdate;
    private RecyclerView recyclerView;

    private int currentPosition;
    private int[] imgs = {R.drawable.car, R.drawable.motorbike, R.drawable.plane};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        tourAdapter = new TourAdapter((MainActivity)getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(tourAdapter);
        btnAdd.setOnClickListener(view1 -> {
            String i = spinner.getSelectedItem().toString();
            try {
                double price = Double.parseDouble(ePrice.getText().toString());
                int img = imgs[Integer.parseInt(i)];
                String name = eName.getText().toString();
                String time = eTime.getText().toString();
                Tour newTour = new Tour(img, name, time, price);
                tourAdapter.add(newTour);
            }
            catch (NumberFormatException e){
                Toast.makeText(getContext(), "Giá không hợp lệ", Toast.LENGTH_LONG).show();
            }
        });
        btnUpdate.setOnClickListener(view1 -> {
            String i = spinner.getSelectedItem().toString();
            try {
                double price = Double.parseDouble(ePrice.getText().toString());
                int img = imgs[Integer.parseInt(i)];
                String name = eName.getText().toString();
                String time = eTime.getText().toString();
                Tour newTour = new Tour(img, name, time, price);
                tourAdapter.update(newTour, this.currentPosition);
                btnUpdate.setVisibility(View.INVISIBLE);
                btnAdd.setVisibility(View.VISIBLE);
            }
            catch (NumberFormatException e){
                Toast.makeText(getContext(), "Giá không hợp lệ", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initView(View view){
        spinner = view.findViewById(R.id.spinner);
        recyclerView = view.findViewById(R.id.recyclerView);
        SpinnerAdapter adapter = new SpinnerAdapter(this.getContext(), imgs);
        spinner.setAdapter(adapter);
        eName = view.findViewById(R.id.editName);
        eTime = view.findViewById(R.id.editTime);
        ePrice = view.findViewById(R.id.editPrice);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setVisibility(View.INVISIBLE);
        btnUpdate.setVisibility(View.VISIBLE);
        this.currentPosition = position;
        Tour selectedTour = tourAdapter.getItem(position);
        spinner.setSelection(selectedTour.getImg());
        eName.setText(selectedTour.getName());
        ePrice.setText(selectedTour.getPrice() + "");
        eTime.setText(selectedTour.getTime());
    }
}
