package com.example.argreeting.ui.messages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.bean.ListResponse;
import com.example.argreeting.bean.Message;
import com.example.argreeting.bean.User;
import com.example.argreeting.network.RetrofitUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesViewModel extends ViewModel {

//    private final MutableLiveData<List<Message>> messages;
    private String postcode;
    private int page;
    private RetrofitUtil networkUtil;

    public MessagesViewModel() throws IOException {
        this.postcode = "test_code";
        this.page = 1;
//        this.messages = new MutableLiveData<>();
//        List<Message> messages = new ArrayList<>();
//        networkUtil = new RetrofitUtil();
//        Call<ResponseBody> raw = networkUtil.userFetchMessages(this.postcode, this.page);
//        raw.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });
//        if (response.getCode() == 0) {
//            messages = response.getData();
//        }
//        this.messages.setValue(messages);
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
//        messages.add(new Message(new User(1, "Eddy Zheng", "https://123123.com",1), 1, "TA21 8NT", 1, "hello world"));
    }

//    public LiveData<List<Message>> getMessages() {
//        return messages;
//    }

}