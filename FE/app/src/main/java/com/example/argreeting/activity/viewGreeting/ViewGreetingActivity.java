package com.example.argreeting.activity.viewGreeting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.argreeting.R;
import com.example.argreeting.activity.login.LoginViewModel;
import com.example.argreeting.databinding.ActivityViewGreetingBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewGreetingActivity extends AppCompatActivity {

    private ViewGreetingViewModel viewGreetingViewModel;
    private ActivityViewGreetingBinding binding;
    private List<String> messageList;
    private String TAG = "ViewGreeting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_greeting);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_greeting);
        ViewModelProvider.Factory factory =(ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        viewGreetingViewModel = viewModelProvider.get(ViewGreetingViewModel.class);
        binding.setViewModel(viewGreetingViewModel);
        binding.setActivityProxy(this);
        binding.setLifecycleOwner(this);

        this.messageList = new ArrayList<>();
        this.messageList.add("Congratulations!!!");
        this.messageList.add("Merry Christmas!");
        this.messageList.add("Happy Valentine\\'s Day!");
        this.messageList.add("Happy Birthday!");
        this.messageList.add("Have a nice day!");
        this.messageList.add("Hope you all the best!");
        this.messageList.add("Happy New Year!");

        Bundle extras = getIntent().getExtras();
        String username = extras.getString("username");
        Log.e(TAG, "onCreate: " + username);
        viewGreetingViewModel.setSenderUsername(extras.getString("username"));
        Log.e(TAG, "onCreate: " + extras.getInt("messageID"));
//        String message = this.messageList.get(extras.getInt("messageID"));
        viewGreetingViewModel.setMessage("Have a nice day!");
    }
}