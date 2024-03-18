package com.lesson9.tablayoutcrud.model;

public class Tour {
    private int img;
    private String name, time;

    private Double price;

    public Tour(int img, String name, String time, Double price) {
        this.img = img;
        this.name = name;
        this.time = time;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
