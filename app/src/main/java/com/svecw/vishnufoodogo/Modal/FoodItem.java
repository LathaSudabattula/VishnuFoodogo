package com.svecw.vishnufoodogo.Modal;

public class FoodItem {
    String fname;
    int cost;
    String quantity;
    String imageURL;
    String frating;
    String fstatus;

    public FoodItem(String fname, int cost, String quantity, String imageURL, String frating, String fstatus) {
        this.fname = fname;
        this.cost = cost;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.frating = frating;
        this.fstatus = fstatus;
    }

    public FoodItem() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getFrating() {
        return frating;
    }

    public void setFrating(String frating) {
        this.frating = frating;
    }

    public String getFstatus() {
        return fstatus;
    }

    public void setFstatus(String fstatus) {
        this.fstatus = fstatus;
    }
}
