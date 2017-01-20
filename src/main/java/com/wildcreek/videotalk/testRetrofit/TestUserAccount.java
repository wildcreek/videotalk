package com.wildcreek.videotalk.testRetrofit;

import com.google.gson.Gson;
import com.wildcreek.videotalk.model.response.LoginResponse;
import com.wildcreek.videotalk.model.ResultModelTest;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

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
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
    static UserApi userApi = retrofit.create(UserApi.class);
    public static void main(String args[]) {
        testLogin();
    }

    public interface UserApi{
        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/phone/register")
        Observable<ResultModelTest<LoginResponse>> register(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/phone/login")
        Observable<ResultModelTest<LoginResponse>> login(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/phone/auth_login")
        Observable<ResultModelTest<LoginResponse>> authLogin(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/stb/auth_login")
        Observable<ResultModelTest<LoginResponse>> stbAuthLogin(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/phone/modify_password")
        Observable<ResultModelTest<LoginResponse>> modifyPwd(@Body RequestBody body);

        @Headers({"Content-Type: application/json","Accept: application/json"})
        @POST("account/logout")
        Observable<ResultModelTest<LoginResponse>> logout(@Body RequestBody body);

    }
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable
                //.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.immediate())//子线程访问网络
                //.observeOn(Schedulers.immediate())//回调到主线程
                .subscribe(observer);
    }

    public static void login(String username, String password,Observer<ResultModelTest<LoginResponse>> observer){
        HashMap<String,String> params = new HashMap<>();
        params.put("clientID","11222233");
        params.put("userAccount",username);
        params.put("password",password);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(params));
        setSubscribe(userApi.login(body),observer);
    }

    public static void testLogin(){
//        Call<ResultModelTest<LoginResponse>> call = userApi.login(body);
//        call.enqueue(new Callback<ResultModelTest<LoginResponse>>() {
//            @Override
//            public void onResponse(Call<ResultModelTest<LoginResponse>> call, Response<ResultModelTest<LoginResponse>> response) {
//                System.out.println("登录返回结果：" + response.code() + response.message() + ((LoginResponse)response.body().getContent()).userToken);
//
//            }
//
//            @Override
//            public void onFailure(Call<ResultModelTest<LoginResponse>> call, Throwable throwable) {
//                System.out.println("登录返回失败：" + throwable.toString());
//            }
//        });
        TestUserAccount.login("18903769013", "12345", new Observer<ResultModelTest<LoginResponse>>() {
            @Override
            public void onCompleted() {
                System.out.println("登录返回onCompleted：");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("登录返回失败：" + throwable.toString());
            }

            @Override
            public void onNext(ResultModelTest<LoginResponse> response) {
                System.out.println("登录返回结果：" + response );
            }
        });
    }

}
