package com.example.argreeting.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class SendMessage {

    private String sendPostcode;
    private int contentID;
    private int aniID;
    private int emojiID;

    public SendMessage(String sendPostcode, int contentID, int aniID, int emojiID) {
        this.sendPostcode = sendPostcode;
        this.contentID = contentID;
        this.aniID = aniID;
        this.emojiID = emojiID;
    }

    public String getPostcode() {
        return sendPostcode;
    }

    public void setPostcode(String sendPostcode) {
        this.sendPostcode = sendPostcode;
    }

    public int getContentID() {
        return contentID;
    }

    public void setContentID(int contentID) {
        this.contentID = contentID;
    }

    public int getAniID() {
        return aniID;
    }

    public void setAniID(int aniID) {
        this.aniID = aniID;
    }

    public int getEmojiID() {
        return emojiID;
    }

    public void setEmojiID(int emojiID) {
        this.emojiID = emojiID;
    }
}
