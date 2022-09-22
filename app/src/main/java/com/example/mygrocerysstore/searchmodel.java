package com.example.mygrocerysstore;

public class searchmodel {
    String sname,sprice,simage;

    public searchmodel(String sname, String sprice, String simage) {
        this.sname = sname;
        this.sprice = sprice;
        this.simage = simage;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public String getSimage() {
        return simage;
    }

    public void setSimage(String simage) {
        this.simage = simage;
    }
}
