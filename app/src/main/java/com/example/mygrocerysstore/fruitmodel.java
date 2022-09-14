package com.example.mygrocerysstore;

public class fruitmodel {
    String fname,fprice,fimage;

    public fruitmodel(){}

    public fruitmodel(String fname, String fprice, String fimage) {
        this.fname = fname;
        this.fprice = fprice;
        this.fimage = fimage;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFprice() {
        return fprice;
    }

    public void setFprice(String fprice) {
        this.fprice = fprice;
    }

    public String getFimage() {
        return fimage;
    }

    public void setFimage(String fimage) {
        this.fimage = fimage;
    }
}
