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

    private MutableLiveData<List<Message>> messages;

    public MessagesViewModel(){
        this.messages = new MutableLiveData<>();
        List<Message> messages = new ArrayList<>();
        this.messages.setValue(messages);
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages.postValue(messages);
    }

    public List<Message> getMessageList() {
        List<Message> messageList = this.messages.getValue();
        return messageList;
    }
}