package com.example.sugarorm.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;

public class Person extends SugarRecord {
    private String fullName;
    private Integer age;
    private String address;
    private Company company;

    public Person(String fullName) {
        this.fullName = fullName;
    }

    public Person(String fullName, Company company) {
        this.fullName = fullName;
        this.company = company;
    }

    public Person(String fullName, Integer age, String address) {
        this.fullName = fullName;
        this.age = age;
        this.address = address;
    }

    public Person(String fullName, Integer age, String address, Company company) {
        this.fullName = fullName;
        this.age = age;
        this.address = address;
        this.company = company;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @NonNull
    @Override
    public String toString() {
        return getFullName() + ", " + getCompany().getName();
    }
}
