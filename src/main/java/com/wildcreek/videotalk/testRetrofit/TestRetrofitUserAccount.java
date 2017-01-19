package com.wildcreek.videotalk.testRetrofit;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestRetrofitUserAccount {
    public static final String BASE_URL = "https://192.168.1.133:8443/videotalk/";
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static void main(String args[]) {
        testLogin();
    }
    public static class LoginResponse {
        public final String userID;
        public final String phoneNumber;
        public final String changeDevice;
        public final String userToken;
        public final String expireTime;

        public LoginResponse(String userID, String phoneNumber, String changeDevice, String userToken, String expireTime) {
            this.userID = userID;
            this.phoneNumber = phoneNumber;
            this.changeDevice = changeDevice;
            this.userToken = userToken;
            this.expireTime = expireTime;
        }
    }

    public interface UserApi{
        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("/account/phone/login")
        Call<LoginResponse> login(@Body RequestBody body);
    }

    public static void testLogin(){
        HashMap<String,String> params = new HashMap<>();
        params.put("","");
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(params));
        UserApi userApi = retrofit.create(UserApi.class);
        userApi.login(body);
    }
}
