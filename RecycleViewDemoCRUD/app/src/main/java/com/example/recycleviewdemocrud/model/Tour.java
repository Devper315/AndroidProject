package com.example.recycleviewdemocrud.model;

public class Tour {
    private int img;
    private String path, time;

    public Tour() {
    }

    public Tour(int img, String path, String time) {
        this.img = img;
        this.path = path;
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
