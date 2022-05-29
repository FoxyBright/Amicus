package com.example.amicus.messages;

public class MessagesList {

    private String name;
    private String mobil;
    private String lastMessage;
    private String profilePic;
    private String chatKey;

    private int unseenmessages;

    public MessagesList(String name, String mobil, String lastMessage,String profilePic, int unseenmessages,String chatKey) {
        this.name = name;
        this.mobil = mobil;
        this.lastMessage = lastMessage;
        this.unseenmessages = unseenmessages;
        this.profilePic = profilePic;
        this.chatKey = chatKey;
    }

    public String getChatKey() {
        return chatKey;
    }

    public String getName() {
        return name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getMobil() {
        return mobil;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnseenmessages() {
        return unseenmessages;
    }
}
