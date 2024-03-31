package com.example.sugarorm.model;

import com.orm.SugarRecord;

public class Company extends SugarRecord {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
