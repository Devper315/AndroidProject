package com.example.quizzapp.model;

public class Result {
    private int id;


    private String score;
    private String userId, datetime;

    public Result(String score, String userId, String datetime) {
        this.score = score;
        this.userId = userId;
        this.datetime = datetime;
    }

    public Result(int id, String score, String userId, String datetime) {
        this.id = id;
        this.score = score;
        this.userId = userId;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
