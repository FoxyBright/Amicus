package com.example.amicus.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateBody  {

    private String autoid;
    private String statenumber;
    private String model;
    private String color;
    private String places;

    public void setAutoid(String autoid) {
        this.autoid = autoid;
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
