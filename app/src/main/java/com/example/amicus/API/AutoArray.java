package com.example.amicus.API;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class AutoArray {
    //@SerializedName("remind")
    @Expose
    private ArrayList<AutoResponce> autoResponces = new ArrayList<>();


    public ArrayList<AutoResponce> getAutoResponces() {
        return autoResponces;
    }

    public void setContacts(ArrayList<AutoResponce> autoResponces) {
        this.autoResponces = autoResponces;
    }
}
