package com.example.argreeting.bean;

public class MessageTest {
    private int animationID;
    private String avatar;
    private String create_time;
    private int gender;
    private int messageID;
    private String username;

    public MessageTest(int animationID, String avatar, String create_time, int gender, int messageID, String username) {
        this.animationID = animationID;
        this.avatar = avatar;
        this.create_time = create_time;
        this.gender = gender;
        this.messageID = messageID;
        this.username = username;
    }

    public int getAnimationID() {
        return animationID;
    }

    public void setAnimationID(int animationID) {
        this.animationID = animationID;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "MessageTest{" +
                "animationID=" + animationID +
                ", avatar='" + avatar + '\'' +
                ", create_time='" + create_time + '\'' +
                ", gender=" + gender +
                ", messageID=" + messageID +
                ", username='" + username + '\'' +
                '}';
    }
}
