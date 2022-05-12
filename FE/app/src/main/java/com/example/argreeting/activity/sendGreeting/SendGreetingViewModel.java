package com.example.argreeting.activity.sendGreeting;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.argreeting.R;
import com.example.argreeting.bean.SendMessage;

import java.util.Arrays;
import java.util.List;

public class SendGreetingViewModel extends ViewModel {

    private SendMessage sendMessage;
    private MutableLiveData<String> postcode;

    public SendGreetingViewModel() {
        this.postcode = new MutableLiveData<>();
        this.postcode.setValue("");
        this.sendMessage = new SendMessage("", -1, -1, -1);
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setSendMessageContent(int id) {
        this.sendMessage.setContentID(id);
    }

    public void setSendMessageAnimation(int id) {
        this.sendMessage.setAniID(id);
    }

    public void setSendMessageEmoji(int id) {
        this.sendMessage.setEmojiID(id);
    }


    public MutableLiveData<String> getPostcode() {
        return postcode;
    }
}
