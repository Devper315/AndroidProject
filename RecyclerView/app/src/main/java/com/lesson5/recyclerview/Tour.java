package com.lesson5.recyclerview;

public class Tour {
    private int img;
    private String name;

    public Tour(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public Tour() {
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
}
