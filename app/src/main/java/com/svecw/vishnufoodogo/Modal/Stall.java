package com.svecw.vishnufoodogo.Modal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stall {
    String id;
    String name;
    String imageURL;
    String status;
    String timings;
    String rating;

    Map<String, Object> fitems = new HashMap<String, Object>(); ;

    public Stall() {
    }

    public Stall(String id, String name, String imageURL, String status, String timings, String rating, Map<String, Object> fitems) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.status = status;
        this.timings = timings;
        this.rating = rating;
        this.fitems = fitems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Map<String, Object> getFitems() {
        return fitems;
    }

    public void setFitems(Map<String, Object> fitems) {
        this.fitems = fitems;
    }
}
