package com.example.argreeting.bean;

import android.graphics.Bitmap;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class SignupUser extends BaseObservable {
    private String signupUsername;
    private String signupPassword;
    private String signupPassword2;
    private Bitmap signupAvatar;

    public SignupUser(String signupUsername, String signupPassword, String signupPassword2, Bitmap signupAvatar) {
        this.signupUsername = signupUsername;
        this.signupPassword = signupPassword;
        this.signupPassword2 = signupPassword2;
        this.signupAvatar = signupAvatar;
    }

    @Bindable
    public String getSignupUsername() {
        return signupUsername;
    }

    public void setSignupUsername(String signupUsername) {
        this.signupUsername = signupUsername;
        notifyPropertyChanged(BR.signupUsername);
    }

    @Bindable
    public String getSignupPassword2() {
        return signupPassword2;
    }

    public void setSignupPassword2(String signupPassword2) {
        this.signupPassword2 = signupPassword2;
        notifyPropertyChanged(BR.signupPassword2);
    }

    @Bindable
    public String getSignupPassword() {
        return signupPassword;
    }

    public void setSignupPassword(String signupPassword) {
        this.signupPassword = signupPassword;
        notifyPropertyChanged(BR.signupPassword);
    }

    @Bindable
    public Bitmap getSignupAvatar() {
        return signupAvatar;
    }

    public void setSignupAvatar(Bitmap signupAvatar) {
        this.signupAvatar = signupAvatar;
        notifyPropertyChanged(BR.signupAvatar);
    }
}


