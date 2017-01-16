package com.wildcreek.videotalk.testRetrofit;

import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.*;
import com.squareup.okhttp.Request.Builder;
import com.wildcreek.videotalk.test.IseeUrl;
import retrofit2.Retrofit;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.io.IOException;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestRetrofitUserAccount {
    public static final String BASE_URL = "http://api.myservice.com";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static void main(String args[]) {
        testLogin();
        //testRegister();
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
        @POST("/repos/{owner}/{repo}/contributors")
        retrofit2.Call<LoginResponse> login(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }
    private static void testLogin() {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        JSONObject params = new JSONObject();
        params.put("userAccount", "18812345679");
        params.put("password", "12345");
        params.put("clientID", "5655655959545");
        RequestBody requestBody = RequestBody.create(mediaType, params.toJSONString());
        System.out.println("登录请求参数:" + params.toJSONString());
        Request request = new Builder().url(IseeUrl.PHONE_LOGIN_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Request arg0, IOException arg1) {
                System.out.print("登录失败" + arg0.body().toString() + arg1.toString());
            }

            public void onResponse(Response arg0) throws IOException {
                System.out.print("登录成功" + arg0.body().string());
            }
        });
    }

    private static void testRegister() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        JSONObject params = new JSONObject();
        params.put("phoneNumber", "18812345679");
        params.put("password", "12345");
        params.put("smsCode", "12345");
        params.put("nickname", "12345");
        params.put("clientID", "999999999");
        RequestBody requestBody = RequestBody.create(mediaType, params.toJSONString());
        System.out.println("注册请求参数:" + params.toJSONString());
        Request request = new Builder().url(IseeUrl.PHONE_REGISTER_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Request arg0, IOException arg1) {
                System.out.print("注册失败" + arg0.body().toString() + arg1.toString());
            }

            public void onResponse(Response arg0) throws IOException {
                System.out.print("注册成功" + arg0.body().string());
            }
        });
    }
}
