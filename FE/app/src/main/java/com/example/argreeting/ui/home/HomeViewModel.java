package com.example.argreeting.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.argreeting.bean.User;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> username;

    public HomeViewModel() {
        username = new MutableLiveData<>();
        User user = new User(1, "Eddy Zheng", "https://12345.com/avatar.png", 1);
        username.postValue(user.getUsername());
    }

    public LiveData<String> getUsername() {
        return username;
    }

    public void buttonOnclickAction() {

    }
}