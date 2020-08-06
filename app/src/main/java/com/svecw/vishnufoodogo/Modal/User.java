package com.svecw.vishnufoodogo.Modal;

import java.util.ArrayList;

public class User {
    String id;
    String username;
    String imageURL;
    ArrayList<Stall> favouritestalls;
    ArrayList<FoodItem> favouritefitems;

    public User(String id, String name, String imageURL, ArrayList<Stall> favouritestalls, ArrayList<FoodItem> favouritefitems) {
        this.id = id;
        this.username = name;
        this.imageURL = imageURL;
        this.favouritestalls = favouritestalls;
        this.favouritefitems = favouritefitems;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ArrayList<Stall> getFavouritestalls() {
        return favouritestalls;
    }

    public void setFavouritestalls(ArrayList<Stall> favouritestalls) {
        this.favouritestalls = favouritestalls;
    }

    public ArrayList<FoodItem> getFavouritefitems() {
        return favouritefitems;
    }

    public void setFavouritefitems(ArrayList<FoodItem> favouritefitems) {
        this.favouritefitems = favouritefitems;
    }
}
