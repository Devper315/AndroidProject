package com.example.recycleviewdemocrud.utils;

import android.annotation.SuppressLint;

import com.example.recycleviewdemocrud.R;
import com.example.recycleviewdemocrud.model.Tour;
import com.example.recycleviewdemocrud.model.TourAdapter;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    // image for all view
    public static int[] imgs = {R.drawable.car, R.drawable.motorbike, R.drawable.plane};
    public static List<Tour> tourListShow = new ArrayList<>();
    public static List<Tour> tourListBackup = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public static void searchTour(String s, TourAdapter adapter) {
        List<Tour> tourListFiltered = new ArrayList<>();
        for (Tour i : tourListBackup) {
            if (i.getPath().toLowerCase().contains(s.toLowerCase())) {
                tourListFiltered.add(i);
            }
        }
        tourListShow = tourListFiltered;
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public static void addTour(Tour tour, TourAdapter adapter){
        tourListBackup.add(tour);
        tourListShow = tourListBackup;
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public static void updateTourList(Tour tour, int position, TourAdapter adapter){
        tourListBackup.set(position, tour);
        tourListShow = tourListBackup;
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public static void removeTour(int position, TourAdapter adapter){
        tourListBackup.remove(position);
        tourListShow = tourListBackup;
        adapter.notifyDataSetChanged();
    }
}
