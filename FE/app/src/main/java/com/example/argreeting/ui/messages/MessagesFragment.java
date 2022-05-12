package com.example.argreeting.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.argreeting.R;
import com.example.argreeting.bean.ListResponse;
import com.example.argreeting.bean.Message;
import com.example.argreeting.databinding.FragmentMessagesBinding;
import com.example.argreeting.network.RetrofitUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;
    private MessagesViewModel messagesViewModel;
    private MessagesRecViewAdapter messagesRecViewAdapter;
    private RetrofitUtil networkUtil;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_messages, container, false);

        messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);

        messagesRecViewAdapter = new MessagesRecViewAdapter();

        binding = FragmentMessagesBinding.bind(root);

        binding.recViewMessage.setAdapter(messagesRecViewAdapter);

        networkUtil = new RetrofitUtil();

        try {
            Call<ResponseBody> raw = networkUtil.userFetchMessages("test_code", 1);
            raw.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Gson listGson = new Gson();
                    Type messageListType = new TypeToken<ListResponse<Message>>(){}.getType();
                    ListResponse<Message> listResponse = null;
                    try {
                        listResponse = listGson.fromJson(response.body().string(), messageListType);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (listResponse.getCode() == 0) {
                        messagesRecViewAdapter.setMessages(listResponse.getData());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        binding.recViewMessage.setLayoutManager(new LinearLayoutManager(root.getContext(), RecyclerView.VERTICAL, false));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}