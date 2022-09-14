package com.example.mygrocerysstore;

public class orderviewmodel {
    String oname,oprice,oquantity;

    public orderviewmodel(String oname, String oprice, String oquantity) {
        this.oname = oname;
        this.oprice = oprice;
        this.oquantity = oquantity;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOprice() {
        return oprice;
    }

    public void setOprice(String oprice) {
        this.oprice = oprice;
    }

    public String getOquantity() {
        return oquantity;
    }

    public void setOquantity(String oquantity) {
        this.oquantity = oquantity;
    }
}
