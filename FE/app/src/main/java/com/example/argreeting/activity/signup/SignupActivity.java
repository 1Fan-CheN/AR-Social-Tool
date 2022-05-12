package com.example.argreeting.activity.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.argreeting.MainActivity;
import com.example.argreeting.R;
import com.example.argreeting.activity.login.LoginActivity;
import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.bean.LoginUser;
import com.example.argreeting.bean.SignupUser;
import com.example.argreeting.databinding.ActivitySignupBinding;
import com.example.argreeting.network.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private SignupViewModel signupViewModel;
    private String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        signupViewModel = viewModelProvider.get(SignupViewModel.class);
        binding.setViewModel(signupViewModel);
        binding.setActivityProxy(this);
        binding.setLifecycleOwner(this);
    }

    public void signup() {
        RetrofitUtil networkUtil = new RetrofitUtil();
        SignupUser user = this.signupViewModel.getSignupUser().getValue();
        Log.e(TAG, "signup: " + user.getSignupPassword2());
        if (user.getSignupPassword().equals(user.getSignupPassword2())) {
            try {
                Call<BaseResponse<String>> call = networkUtil.userRegisterAction(user.getSignupUsername(), user.getSignupPassword(), 0);
                call.enqueue(new Callback<BaseResponse<String>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                        if (response.body().getCode() == 0) {
                            Toast.makeText(SignupActivity.this, "Sign up Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        }
                        else {
                            Toast.makeText(SignupActivity.this, "Failed to sign up", Toast.LENGTH_SHORT).show();
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
        else {
            Toast.makeText(SignupActivity.this, "Passwords do match", Toast.LENGTH_SHORT).show();
        }
    }
}