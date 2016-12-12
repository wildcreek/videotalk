package com.wildcreek.videotalk.test;

import com.alibaba.fastjson.JSONObject;
import com.wildcreek.videotalk.Constant;
import com.squareup.okhttp.*;
import com.squareup.okhttp.Request.Builder;

import java.io.IOException;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestUserAccount {

    public static void main(String args[]) {
        testLogin();
    }

    private static void testLogin() {
//        OkHttpClient client = new OkHttpClient();
//        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
//        builder.addFormDataPart("clientID", "6666");
//        builder.addFormDataPart("clientType", "phone");
//        builder.addFormDataPart("userAccount", "testUserAccount");
//        builder.addFormDataPart("loginType", "1");
//        RequestBody requestBody = builder.build();

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        JSONObject params = new JSONObject();
        params.put("userAccount", "66666");
        params.put("clientType", "phone");
        params.put("clientID", "66666");
        params.put("loginType", "3");
        RequestBody requestBody = RequestBody.create(mediaType, params.toJSONString());
        System.out.println("登录请求参数:" + params.toJSONString());
        Request request = new Builder().url(Constant.USER_LOGIN_URL).post(requestBody)
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
}
