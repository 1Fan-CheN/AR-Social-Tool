package com.example.argreeting.activity.signup;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.argreeting.bean.SignupUser;

public class SignupViewModel extends ViewModel {
    private MutableLiveData<SignupUser> signupUser;

    public SignupViewModel() {
        this.signupUser = new MutableLiveData<>();
        this.signupUser.setValue(new SignupUser("", "", "", null));
    }

    public LiveData<SignupUser> getSignupUser() {
        return signupUser;
    }

    public void setSignupUser(SignupUser signupUser) {
        this.signupUser.postValue(signupUser);
    }
}
