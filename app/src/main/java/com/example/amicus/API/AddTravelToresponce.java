package com.example.amicus.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddTravelToresponce {
    @SerializedName("response")
    @Expose
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
