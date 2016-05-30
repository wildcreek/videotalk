package com.mateo.videotalk.test;

import com.mateo.videotalk.Constant;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Request.Builder;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestUserAccount {

    public static void main(String args[]) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        MediaType JSON1 = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON1, "{\"clientType\":\"0\",\"clientID\":\"1\",\"userAccount\":\"2\",\"loginType\":\"3\"}");
        Request request = (new Builder()).url(Constant.USER_LOGIN_URL).post(body).build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            public void onFailure(Request arg0, IOException arg1) {
                System.out.print("失败" + arg0.body().toString() + arg1.toString());
            }

            public void onResponse(Response arg0) throws IOException {
                System.out.print("成功" + arg0.body().string());
            }
        });
    }
}
