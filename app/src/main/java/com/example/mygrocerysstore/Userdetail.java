package com.example.mygrocerysstore;

import java.io.Serializable;

public class Userdetail implements Serializable {
    String amobile, city, email, mobile, name, state,street;

    public Userdetail() {

    }

    public Userdetail(String amobile, String city, String email, String mobile, String name, String state,String street) {
        this.amobile = amobile;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
        this.name = name;
        this.state = state;
        this.street = street;
    }

    public String getAmobile() {
        return amobile;
    }

    public void setAmobile(String amobile) {
        this.amobile = amobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}