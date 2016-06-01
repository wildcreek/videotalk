package com.mateo.videotalk.test;

import com.mateo.videotalk.Constant;
import com.squareup.okhttp.*;
import com.squareup.okhttp.Request.Builder;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestUserAccount {

    public static void main(String args[]) {
        OkHttpClient client = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("clientID", "testClientID");
        builder.addFormDataPart("clientType", "testClientType");
        builder.addFormDataPart("userAccount", "testUserAccount");
        builder.addFormDataPart("loginType", "testLoginType");
        RequestBody requestBody = builder.build();
        Request request = new Builder().url(Constant.USER_LOGIN_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Request arg0, IOException arg1) {
                System.out.print("失败" + arg0.body().toString() + arg1.toString());
            }

            public void onResponse(Response arg0) throws IOException {
                System.out.print("成功" + arg0.body().string());
            }
        });
    }
}
