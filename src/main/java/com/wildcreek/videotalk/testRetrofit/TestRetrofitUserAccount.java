package com.wildcreek.videotalk.testRetrofit;

import com.squareup.okhttp.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestRetrofitUserAccount {
    public static final String BASE_URL = "https://192.168.1.133:8443/videotalk";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static void main(String args[]) {

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

    public interface UserAccount{
        @POST("/account/phone/login")
        retrofit2.Call<LoginResponse> login(@Body RequestBody body);
    }

}
