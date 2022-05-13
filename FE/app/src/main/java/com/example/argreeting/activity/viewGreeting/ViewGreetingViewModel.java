package com.example.argreeting.activity.viewGreeting;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewGreetingViewModel extends ViewModel {
    private MutableLiveData<String> senderUsername;
    private MutableLiveData<String> message;

    public ViewGreetingViewModel() {
        this.senderUsername = new MutableLiveData<>();
        this.message = new MutableLiveData<>();
        this.senderUsername.setValue("");
        this.message.setValue("");

    }

    public LiveData<String> getSendUsername() {
        return this.senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername.setValue(senderUsername);
    }

    public LiveData<String> getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message.setValue(message);
    }

}
