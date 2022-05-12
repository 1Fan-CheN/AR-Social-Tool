package com.example.argreeting.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.argreeting.MainActivity;
import com.example.argreeting.R;
import com.example.argreeting.activity.sendGreeting.SendGreetingActivity;
import com.example.argreeting.activity.signup.SignupActivity;
import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.bean.LoginUser;
import com.example.argreeting.databinding.ActivityLoginBinding;
import com.example.argreeting.network.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private String TAG = "Login Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        ViewModelProvider.Factory factory =(ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        loginViewModel = viewModelProvider.get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setActivityProxy(this);
        binding.setLifecycleOwner(this);

    }

    public void userlogin() {
        RetrofitUtil networkUtil = new RetrofitUtil();
        LoginUser user = this.loginViewModel.getLoginUser().getValue();
        try {
            Call<BaseResponse<String>> call = networkUtil.userLoginAction(user.getLoginUsername(), user.getLoginPassword());
            call.enqueue(new Callback<BaseResponse<String>>() {
                @Override
                public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                    if (response.body().getCode() == 0) {
                        Log.e(TAG, "onResponse: success");
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void register() {
        startActivity(new Intent(this, SignupActivity.class));
    }
}