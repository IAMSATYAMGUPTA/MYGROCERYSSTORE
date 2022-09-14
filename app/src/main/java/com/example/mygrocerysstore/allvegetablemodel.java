package com.example.mygrocerysstore;

public class allvegetablemodel {
    String vaimage,vaname,vaprice;

    public allvegetablemodel(){}

    public allvegetablemodel(String vaimage, String vaname, String vaprice) {
        this.vaimage = vaimage;
        this.vaname = vaname;
        this.vaprice = vaprice;
    }

    public String getVaimage() {
        return vaimage;
    }

    public void setVaimage(String vaimage) {
        this.vaimage = vaimage;
    }

    public String getVaname() {
        return vaname;
    }

    public void setVaname(String vaname) {
        this.vaname = vaname;
    }

    public String getVaprice() {
        return vaprice;
    }

    public void setVaprice(String vaprice) {
        this.vaprice = vaprice;
    }
}

