package com.example.quizzapp.model;

public class User {
    private int id;
    private String name, firebaseId;

    public User(int id, String name, String firebaseId) {
        this.id = id;
        this.name = name;
        this.firebaseId = firebaseId;
    }

    public User(String name, String firebaseId) {
        this.name = name;
        this.firebaseId = firebaseId;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }
}
