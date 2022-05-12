package com.example.argreeting.network;

import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.bean.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NetworkService {

    @POST("/user/login")
    Call<BaseResponse<String>> userLogin(@Body RequestBody requestBody);

    @POST("/user/create_user")
    Call<BaseResponse<String>> userRegister(@Body RequestBody requestBody);

    @POST("/greeting/get_list")
    Call<ResponseBody> userFetchMessages(@Body RequestBody requestBody);

    @POST("/greeting/send")
    Call<BaseResponse<String>> userSendMessage(@Body RequestBody requestBody);

    @POST("/user/update_passwd")
    Call<BaseResponse<String>> userUpdatePassword(@Body RequestBody requestBody);
}
