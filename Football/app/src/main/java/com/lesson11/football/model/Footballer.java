package com.lesson11.football.model;

public class Footballer {
    private String fullname, birthday, gender;
    private Boolean isOne, isTwo, isThree;

    public Footballer(String fullname, String birthday, String gender, Boolean isOne, Boolean isTwo, Boolean isThree) {
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.isOne = isOne;
        this.isTwo = isTwo;
        this.isThree = isThree;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getOne() {
        return isOne;
    }

    public void setOne(Boolean one) {
        isOne = one;
    }

    public Boolean getTwo() {
        return isTwo;
    }

    public void setTwo(Boolean two) {
        isTwo = two;
    }

    public Boolean getThree() {
        return isThree;
    }

    public void setThree(Boolean three) {
        isThree = three;
    }
}
