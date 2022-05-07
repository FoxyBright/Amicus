package com.example.amicus.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddBodyAuto {

    private String statenumber;
    private String model;
    private String color;
    private String places;
    private int owner;

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setStatenumber(String statenumber) {
        this.statenumber = statenumber;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}

