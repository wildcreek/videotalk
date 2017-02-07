package com.wildcreek.videotalk.testRetrofit;

import com.google.gson.Gson;
import com.wildcreek.videotalk.model.ResultModel;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestUserAccount {
    public static final String BASE_URL = "https://192.168.1.133:8443/videotalk/";
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(HttpsUtils.getOkHttpsClient())
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    static UserApi userApi = retrofit.create(UserApi.class);
    public static void main(String args[]) {
        login("18903769013","12345");
    }

    public interface UserApi{
        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/phone/register")
        //Observable<ResultModel<LoginResponse>> register(@Body RequestBody body);
        Call<ResultModel> register(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/phone/login")
        Call<ResultModel> login(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/phone/auth_login")
        Call<ResultModel> authLogin(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/stb/auth_login")
        Call<ResultModel> stbAuthLogin(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/phone/modify_password")
        Call<ResultModel> modifyPwd(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/logout")
        Call<ResultModel> logout(@Body RequestBody body);

    }
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable
                //.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.immediate())//子线程访问网络
                //.observeOn(Schedulers.immediate())//回调到主线程
                .subscribe(observer);
    }

    public static void login(String username, String password){
        HashMap<String,String> params = new HashMap<>();
        params.put("clientID","11222233");
        params.put("userAccount",username);
        params.put("password",password);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(params));
        userApi.login(body).enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {

                if(response.isSuccessful()){
                    System.out.println("登录返回成功：" + response.body().toString());
                }else{
                    try {
                        System.out.println("登录返回失败：" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable throwable) {
                System.out.println("登录返回失败：" + throwable.toString());
            }
        });
    }

}
