package com.example.argreeting.activity.sendGreeting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.argreeting.MainActivity;
import com.example.argreeting.R;
import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.databinding.ActivitySendGreetingBinding;
import com.example.argreeting.network.RetrofitUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendGreetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SendGreetingViewModel sendGreetingViewModel;
    private ActivitySendGreetingBinding binding;
    private EditText postcode;
    private int content;
    private int animation;
    private int emoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_greeting);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_send_greeting);
//        ViewModelProvider.Factory factory = (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
//        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
//        sendGreetingViewModel = viewModelProvider.get(SendGreetingViewModel.class);
//        binding.setViewModel(sendGreetingViewModel);
        binding.setActivityProxy(this);
        binding.setLifecycleOwner(this);

        Spinner content = (Spinner) binding.spnSendContent;
        Spinner animation = (Spinner) binding.spnSendAnimation;
        Spinner emoji = (Spinner) binding.spnSendEmoji;
        this.postcode = (EditText) binding.edtSendPostcode;

        ArrayAdapter<CharSequence> messageAdapter = ArrayAdapter.createFromResource(this, R.array.messages_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> animationAdapter = ArrayAdapter.createFromResource(this, R.array.animations_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> emojiAdapter = ArrayAdapter.createFromResource(this, R.array.emoji_array, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        messageAdapter.setDropDownViewResource(androidx.transition.R.layout.support_simple_spinner_dropdown_item);
        animationAdapter.setDropDownViewResource(androidx.transition.R.layout.support_simple_spinner_dropdown_item);
        emojiAdapter.setDropDownViewResource(androidx.transition.R.layout.support_simple_spinner_dropdown_item);

        content.setAdapter(messageAdapter);
        animation.setAdapter(animationAdapter);
        emoji.setAdapter(emojiAdapter);

        content.setOnItemSelectedListener(this);
        animation.setOnItemSelectedListener(this);
        emoji.setOnItemSelectedListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spnSendContent:
//                sendGreetingViewModel.setSendMessageContent(i);
                this.content = i;
                break;
            case R.id.spnSendAnimation:
//                sendGreetingViewModel.setSendMessageAnimation(i);
                this.animation = i;
                break;
            case R.id.spnSendEmoji:
//                sendGreetingViewModel.setSendMessageEmoji(i);
                this.emoji = i;
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void sendOnclick() {
        RetrofitUtil networkUtil = new RetrofitUtil();
        String postcodeStr = this.postcode.getText().toString();
        if (!postcodeStr.isEmpty()) {
            try {
                Call<BaseResponse<String>> call = networkUtil.userSendGreeting(postcodeStr, content, 2, animation);
                call.enqueue(new Callback<BaseResponse<String>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                        if (response.body().getCode() == 0) {
                            Toast.makeText(SendGreetingActivity.this, "Send Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(SendGreetingActivity.this, "Failed to send", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                        Toast.makeText(SendGreetingActivity.this, "Failed to send", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(SendGreetingActivity.this, "No postcode provided", Toast.LENGTH_SHORT).show();
        }

    }


}