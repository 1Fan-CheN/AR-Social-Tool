package com.example.argreeting.activity.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.argreeting.bean.LoginUser;
import com.example.argreeting.bean.User;
import com.example.argreeting.network.RetrofitUtil;

import java.io.IOException;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<LoginUser> loginUser;

    public LoginViewModel() {
        this.loginUser = new MutableLiveData<>();
        this.loginUser.setValue(new LoginUser("", ""));
    }

    public LiveData<LoginUser> getLoginUser() {
        return this.loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser.postValue(loginUser);
    }


}
