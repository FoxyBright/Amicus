package com.example.amicus.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserData {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("regdata")
    @Expose
    private String regdata;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRegdata() {
        return regdata;
    }

    public void setRegdata(String regdata) {
        this.regdata = regdata;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}