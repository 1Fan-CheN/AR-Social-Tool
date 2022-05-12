package com.example.argreeting.bean;

public class User {

    private int uid;
    private String username;
    private String avatar;
    private int gender;

    public User(int uid, String username, String avatar, int gender) {
        this.uid = uid;
        this.username = username;
        this.avatar = avatar;
        this.gender = gender;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                '}';
    }

}
