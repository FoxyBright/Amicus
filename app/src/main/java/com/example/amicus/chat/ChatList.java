package com.example.amicus.chat;

public class ChatList {

    String mobile;
    String name;
    String message;
    String date;
    String time;

    public ChatList(String mobile, String name, String message, String date, String time) {
        this.mobile = mobile;
        this.name = name;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
