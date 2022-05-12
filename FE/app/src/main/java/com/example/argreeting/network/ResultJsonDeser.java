package com.example.argreeting.network;

import com.example.argreeting.bean.BaseResponse;
import com.example.argreeting.bean.BaseResponseTest;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ResultJsonDeser implements JsonDeserializer<BaseResponse<?>> {
    @Override
    public BaseResponse<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()){
            System.out.println("in resultjsondeser");
            JsonObject jsonObject = json.getAsJsonObject();
            int code = jsonObject.get("code").getAsInt();
            String msg = jsonObject.get("msg").getAsString();
            if (code != 0) {
                BaseResponse<String> baseResponse = new BaseResponse<>(code, msg, "");
                return baseResponse;
            }
            String data;
            try {
                data = jsonObject.get("data").getAsString();
            } catch (Exception e) {
                data = "";
                e.printStackTrace();
            }

//            Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
//            String list = jsonObject.get("data").getAsString();
//            System.out.println(list);
            BaseResponse<String> baseResponse = new BaseResponse<>(code, msg, data);
            return baseResponse;
        }
        return null;
    }
}
