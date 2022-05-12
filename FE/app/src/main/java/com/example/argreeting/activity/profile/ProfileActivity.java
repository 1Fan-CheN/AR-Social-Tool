package com.example.argreeting.activity.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.argreeting.MainActivity;
import com.example.argreeting.R;
import com.example.argreeting.activity.login.LoginActivity;
import com.example.argreeting.activity.signup.SignupActivity;
import com.example.argreeting.activity.signup.SignupViewModel;
import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.bean.LoginUser;
import com.example.argreeting.bean.ProfileUser;
import com.example.argreeting.databinding.ActivityProfileBinding;
import com.example.argreeting.databinding.ActivitySignupBinding;
import com.example.argreeting.network.RetrofitUtil;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        profileViewModel = viewModelProvider.get(ProfileViewModel.class);
        binding.setViewModel(profileViewModel);
        binding.setActivityProxy(this);
        binding.setLifecycleOwner(this);
    }

    public void userProfileUpdate() {
        RetrofitUtil networkUtil = new RetrofitUtil();
        ProfileUser user = this.profileViewModel.getProfileUser().getValue();
        if (user.getProfilePassword1().equals(user.getProfilePassword2())) {
            try {
                Call<BaseResponse<String>> call = networkUtil.userUpdatePassword(user.getProfilePassword1());
                call.enqueue(new Callback<BaseResponse<String>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                        if (response.body().getCode() == 0) {
                            Toast.makeText(ProfileActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                        }
                        else {
                            Toast.makeText(ProfileActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(ProfileActivity.this, "Passwords do match", Toast.LENGTH_SHORT).show();
        }
    }
}