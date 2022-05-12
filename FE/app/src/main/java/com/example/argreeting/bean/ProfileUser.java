package com.example.argreeting.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.argreeting.BR;

public class ProfileUser extends BaseObservable {
    private String profilePassword1;
    private String profilePassword2;

    public ProfileUser(String profilePassword1, String profilePassword2) {
        this.profilePassword1 = profilePassword1;
        this.profilePassword2 = profilePassword2;
    }

    @Bindable
    public String getProfilePassword1() {
        return profilePassword1;
    }

    public void setProfilePassword1(String profilePassword1) {
        this.profilePassword1 = profilePassword1;
        notifyPropertyChanged(BR.profilePassword1);
    }

    @Bindable
    public String getProfilePassword2() {
        return profilePassword2;
    }

    public void setProfilePassword2(String profilePassword2) {
        this.profilePassword2 = profilePassword2;
        notifyPropertyChanged(BR.profilePassword2);
    }
}
