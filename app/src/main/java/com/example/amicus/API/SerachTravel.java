package com.example.amicus.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SerachTravel {
    @SerializedName("departureplace")
    @Expose
    private String departureplace;
    @SerializedName("arrivalplace")
    @Expose
    private String arrivalplace;
    @SerializedName("departuretime")
    @Expose
    private String departuretime;
    @SerializedName("arrivaltime")
    @Expose
    private String arrivaltime;
    @SerializedName("membercount")
    @Expose
    private Integer membercount;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("weekday")
    @Expose
    private String weekday;
    @SerializedName("automobile")
    @Expose
    private String automobile;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("autor")
    @Expose
    private Integer autor;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("autorname")
    @Expose
    private String autorname;
    @SerializedName("autorphoto")
    @Expose
    private String autorphoto;

    public SerachTravel(String departureplace, String arrivalplace, String departuretime, String arrivaltime, Integer membercount, String distance, String price, String weekday, String automobile, String description, Integer autor, Integer id, String autorname, String autophoto) {
        this.departureplace = departureplace;
        this.arrivalplace = arrivalplace;
        this.departuretime = departuretime;
        this.arrivaltime = arrivaltime;
        this.membercount = membercount;
        this.distance = distance;
        this.price = price;
        this.weekday = weekday;
        this.automobile = automobile;
        this.description = description;
        this.autor = autor;
        this.id = id;
        this.autorname = autorname;
        this.autorphoto = autophoto;
    }

    public String getAutorname() {
        return autorname;
    }

    public void setAutorname(String autorname) {
        this.autorname = autorname;
    }

    public String getAutorphoto() {
        return autorphoto;
    }

    public void setAutorphoto(String autophoto) {
        this.autorphoto = autophoto;
    }

    public String getDepartureplace() {
        return departureplace;
    }

    public void setDepartureplace(String departureplace) {
        this.departureplace = departureplace;
    }

    public String getArrivalplace() {
        return arrivalplace;
    }

    public void setArrivalplace(String arrivalplace) {
        this.arrivalplace = arrivalplace;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public Integer getMembercount() {
        return membercount;
    }

    public void setMembercount(Integer membercount) {
        this.membercount = membercount;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getAutomobile() {
        return automobile;
    }

    public void setAutomobile(String automobile) {
        this.automobile = automobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor)  {
        this.autor = autor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}