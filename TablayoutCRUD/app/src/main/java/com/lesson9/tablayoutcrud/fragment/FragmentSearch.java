package com.lesson9.tablayoutcrud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lesson9.tablayoutcrud.MainActivity;
import com.lesson9.tablayoutcrud.R;
import com.lesson9.tablayoutcrud.adapter.SearchAdapter;
import com.lesson9.tablayoutcrud.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {
    private SearchView searchView;
    private SearchAdapter searchAdapter;
    private RecyclerView recyclerView;
    public static List<Tour> tourList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search);
        recyclerView = view.findViewById(R.id.recyclerViewSearch);
        searchAdapter = new SearchAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(searchAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                return true;
            }
        });
    }

    private void filter(String s){
        List<Tour> filterTour = new ArrayList<>();
        for (Tour tour: tourList){
            if (tour.getName().toLowerCase().contains(s)) filterTour.add(tour);
        }
        if (!filterTour.isEmpty()) searchAdapter.setTourListSearch(filterTour);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.tourList = ((MainActivity) getActivity()).getTourList();
    }
}
