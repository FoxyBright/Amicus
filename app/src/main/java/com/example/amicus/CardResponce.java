package com.example.amicus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardResponce {

    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("cvv")
    @Expose
    private String cvv;
    @SerializedName("banksystem")
    @Expose
    private String banksystem;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getBanksystem() {
        return banksystem;
    }

    public void setBanksystem(String banksystem) {
        this.banksystem = banksystem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
