package com.example.mygrocerysstore;

public class vegetablemodel {
    String vegename,vegeprice,vegeimage;

    public vegetablemodel(){}

    public vegetablemodel(String vegename, String vegeprice, String vegeimage) {
        this.vegename = vegename;
        this.vegeprice = vegeprice;
        this.vegeimage = vegeimage;
    }

    public String getVegename() {
        return vegename;
    }

    public void setVegename(String vegename) {
        this.vegename = vegename;
    }

    public String getVegeprice() {
        return vegeprice;
    }

    public void setVegeprice(String vegeprice) {
        this.vegeprice = vegeprice;
    }

    public String getVegeimage() {
        return vegeimage;
    }

    public void setVegeimage(String vegeimage) {
        this.vegeimage = vegeimage;
    }
}
