package com.example.argreeting.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class LoginUser extends BaseObservable {

    private String loginUsername;
    private String loginPassword;


    public LoginUser(String loginUsername, String loginPassword) {
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
    }

    @Bindable
    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
        notifyPropertyChanged(BR.loginUsername);
    }

    @Bindable
    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
        notifyPropertyChanged(BR.loginPassword);
    }

}
