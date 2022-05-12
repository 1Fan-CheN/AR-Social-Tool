package com.example.argreeting;

import android.util.Log;

import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.bean.ListResponse;
import com.example.argreeting.bean.Message;
import com.example.argreeting.network.NetworkService;
import com.example.argreeting.network.ResultJsonDeser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUnitTest {

    Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(BaseResponse.class, new ResultJsonDeser())
            .create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://212.129.249.105:8110")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    Gson listGson = new Gson();
    Retrofit listRetrofit = new Retrofit.Builder()
            .baseUrl("http://212.129.249.105:8110")
            .build();

    FormBody.Builder builder = new FormBody.Builder();

    NetworkService networkService = retrofit.create(NetworkService.class);
    NetworkService networkServiceList = listRetrofit.create(NetworkService.class);

    @Test
    public void userFetchMessages() throws IOException, JSONException {
        Type messageListType = new TypeToken<ListResponse<Message>>(){}.getType();
//        Call<BaseResponseTest<List<MessageTest>>> fetchMessage = networkServiceList.userFetchMessages("test_code", 1);
//        Response<BaseResponseTest<String>> fetchMessageResponse = fetchMessage.execute();
//        System.out.println(fetchMessageResponse.body());
        HashMap<String, Object> formBody = new HashMap<>();
        formBody.put("postcode", "test_code");
        formBody.put("page", 1);
        JSONObject jsonObject = new JSONObject(formBody);
        RequestBody requestBody = RequestBody.create(String.valueOf(jsonObject), MediaType.parse("application/json"));
        Call<ResponseBody> raw = networkServiceList.userFetchMessages(requestBody);
        Response<ResponseBody> rawResponse = raw.execute();
//        System.out.println(rawResponse.body().string());
        String strResponse = rawResponse.body().string();
//        JSONObject responseJson = new JSONObject(rawResponse.body().string());
        System.out.println(strResponse);
//        ListResponse dataRaw = listGson.fromJson(strResponse, ListResponse.class);
        ListResponse<Message> dataRaw = listGson.fromJson(strResponse, messageListType);
        System.out.println(dataRaw);
//        String data = dataRaw.get("data").getAsString();
//        System.out.println(data);
//        BaseResponse<String> testResponse = gson.fromJson(rawResponse.body().string(), messageListType);
//        ListResponse testResponse = gson.fromJson(rawResponse.body().string(), ListResponse.class);
//        System.out.println(testResponse.getMsg());
    }

    @Test
    public void withoutData() {
        String json = "{\"code\": 0, \"data\": {}, \"msg\": \"success\"}";
        System.out.println(json);
        BaseResponse<String> jsonNoData = listGson.fromJson(json, BaseResponse.class);
        System.out.println(jsonNoData);
        String json2 = "{\"code\": 0, \"data\": {\"uid\": 6}, \"msg\": \"success\"}";
        System.out.println(json2);
        BaseResponse<String> jsonSimple = listGson.fromJson(json2, BaseResponse.class);
        System.out.println(jsonSimple);
    }

    @Test
    public void userRegisterAction() throws  IOException {
        HashMap<String, Object> formBody = new HashMap<>();
        formBody.put("username", "test_user_6");
        formBody.put("gender", 0);
//        String encryptPassword = "";
//        password: 123456
        formBody.put("passwd", "e10adc3949ba59abbe56e057f20f883e");
        JSONObject jsonObject = new JSONObject(formBody);
        RequestBody requestBody = RequestBody.create(String.valueOf(jsonObject), MediaType.parse("application/json"));
        Call<BaseResponse<String>> registerCall = networkService.userRegister(requestBody);
        Response<BaseResponse<String>> registerResponse = registerCall.execute();
        System.out.println(registerResponse.body());
    }

    @Test
    public void md5() {
        String str = "123456";
        String hexStr = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(str.getBytes("utf-8"));
            hexStr = new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(hexStr);
    }

    @Test
    public void userLoginAction() throws IOException {
        HashMap<String, Object> formBody = new HashMap<>();
        formBody.put("username", "test_user_6");
        String encryptPassword = "e10adc3949ba59abbe56e057f20f883e";
        formBody.put("passwd", encryptPassword);
        JSONObject jsonObject = new JSONObject(formBody);
        RequestBody requestBody = RequestBody.create(String.valueOf(jsonObject), MediaType.parse("application/json"));
        Call<BaseResponse<String>> loginCall = networkService.userLogin(requestBody);
        Response<BaseResponse<String>> response = loginCall.execute();
        int code = response.body().getCode();
        System.out.println(response.body().getData());
        System.out.println(code + 1);
    }
}
