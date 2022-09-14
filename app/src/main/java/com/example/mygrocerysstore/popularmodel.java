package com.example.mygrocerysstore;

public class popularmodel {
    String name,discount,description,topimages;

    public popularmodel(){

    }

    public popularmodel(String name, String discount, String description, String topimages) {
        this.name = name;
        this.discount = discount;
        this.description = description;
        this.topimages = topimages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopimages() {
        return topimages;
    }

    public void setTopimages(String topimages) {
        this.topimages = topimages;
    }
}
