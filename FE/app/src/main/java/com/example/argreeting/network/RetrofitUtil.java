package com.example.argreeting.network;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.bean.ListResponse;
import com.example.argreeting.bean.Message;
import com.example.argreeting.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private String TAG = "RetrofitUtil";

    Gson strGson = new GsonBuilder()
            .registerTypeHierarchyAdapter(BaseResponse.class, new ResultJsonDeser())
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://212.129.249.105:8110")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Retrofit strRetrofit = new Retrofit.Builder()
            .baseUrl("http://212.129.249.105:8110")
            .addConverterFactory(GsonConverterFactory.create(strGson))
            .build();
    Retrofit listRetrofit = new Retrofit.Builder()
            .baseUrl("http://212.129.249.105:8110")
            .build();

    NetworkService networkService = retrofit.create(NetworkService.class);
    NetworkService networkServiceStr = strRetrofit.create(NetworkService.class);
    NetworkService networkServiceList = listRetrofit.create(NetworkService.class);

    public String md5(String str) {
        String hexStr = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(str.getBytes("utf-8"));
            hexStr = new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hexStr;
    }

    public Call<BaseResponse<String>> userLoginAction(String username, String password) throws IOException {
        HashMap<String, Object> formBody = new HashMap<>();
        formBody.put("username", username);
        String encryptPassword = this.md5(password);
        formBody.put("passwd", encryptPassword);
        JSONObject jsonObject = new JSONObject(formBody);
        RequestBody requestBody = RequestBody.create(String.valueOf(jsonObject), MediaType.parse("application/json"));
        Call<BaseResponse<String>> loginCall = networkServiceStr.userLogin(requestBody);
        return loginCall;
    }

    public Call<BaseResponse<String>> userRegisterAction(String username, String password, int gender) throws  IOException {
        HashMap<String, Object> formBody = new HashMap<>();
        formBody.put("username", username);
        formBody.put("gender", gender);
        String encryptPassword = this.md5(password);
        formBody.put("passwd", encryptPassword);
        JSONObject jsonObject = new JSONObject(formBody);
        RequestBody requestBody = RequestBody.create(String.valueOf(jsonObject), MediaType.parse("application/json"));
        Call<BaseResponse<String>> registerCall = networkServiceStr.userRegister(requestBody);
        return registerCall;
    }

    public Call<ResponseBody> userFetchMessages(String postcode, int page) throws IOException {
        HashMap<String, Object> formBody = new HashMap<>();
        formBody.put("postcode", postcode);
        formBody.put("page", page);
        JSONObject jsonObject = new JSONObject(formBody);
        RequestBody requestBody = RequestBody.create(String.valueOf(jsonObject), MediaType.parse("application/json"));
        Call<ResponseBody> raw = networkServiceList.userFetchMessages(requestBody);
        return raw;

    }

    public Call<BaseResponse<String>> userSendGreeting(String postcode, int messageID, int uid, int animationID) throws IOException {
        HashMap<String, Object> formBody = new HashMap<>();
        formBody.put("postcode", postcode);
        formBody.put("messageID", messageID);
        formBody.put("uid", uid);
        formBody.put("animationID", animationID);
        JSONObject jsonObject = new JSONObject(formBody);
        RequestBody requestBody = RequestBody.create(String.valueOf(jsonObject), MediaType.parse("application/json"));
        Call<BaseResponse<String>> sendGreetingCall = networkServiceStr.userSendMessage(requestBody);
        return  sendGreetingCall;
    }

    public Call<BaseResponse<String>> userUpdatePassword(String password) throws IOException {
        HashMap<String, Object> formBody = new HashMap<>();
        String encryptPassword = this.md5(password);
        formBody.put("passwd", encryptPassword);
        JSONObject jsonObject = new JSONObject(formBody);
        RequestBody requestBody = RequestBody.create(String.valueOf(jsonObject), MediaType.parse("application/json"));
        Call<BaseResponse<String>> passwdUpdateCall = networkServiceStr.userUpdatePassword(requestBody);
        return passwdUpdateCall;
    }

}
