package com.example.tourmanager.model;

import android.widget.Spinner;

public class Tour {
    private String itinerary, time;
    private int img;
    private double price;
    private Spinner vehicle;

    public Tour() {
    }

    public Tour(String itinerary, String time, int img, double price, Spinner vehicle) {
        this.itinerary = itinerary;
        this.time = time;
        this.img = img;
        this.price = price;
        this.vehicle = vehicle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItinerary() {
        return itinerary;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Spinner getVehicle() {
        return vehicle;
    }

    public void setVehicle(Spinner vehicle) {
        this.vehicle = vehicle;
    }
}
