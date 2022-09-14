package com.example.mygrocerysstore;

public class mycartmodel {
    String orderimage,ordername, orderprice, orderquantity;

    public  mycartmodel(){
    }

    public mycartmodel(String orderimage,String ordername, String orderprice, String orderquantity) {
        this.ordername = ordername;
        this.orderprice = orderprice;
        this.orderquantity = orderquantity;
        this.orderimage = orderimage;
    }

    public String getOrderimage() {
        return orderimage;
    }

    public void setOrderimage(String orderimage) {
        this.orderimage = orderimage;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public String getOrderquantity() {
        return orderquantity;
    }

    public void setOrderquantity(String orderquantity) {
        this.orderquantity = orderquantity;
    }
}
