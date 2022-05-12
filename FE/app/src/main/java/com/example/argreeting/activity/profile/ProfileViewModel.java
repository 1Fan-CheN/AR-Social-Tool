package com.example.argreeting.activity.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.argreeting.bean.LoginUser;
import com.example.argreeting.bean.ProfileUser;
import com.example.argreeting.bean.User;
import com.example.argreeting.network.RetrofitUtil;


public class ProfileViewModel extends ViewModel {

    private MutableLiveData<ProfileUser> profileUser;

    public ProfileViewModel() {
        this.profileUser = new MutableLiveData<>();
        this.profileUser.setValue(new ProfileUser("", ""));
    }

    public LiveData<ProfileUser> getProfileUser() {
        return this.profileUser;
    }

    public void setProfileUser(ProfileUser profileUser) {
        this.profileUser.postValue(profileUser);
    }


}
