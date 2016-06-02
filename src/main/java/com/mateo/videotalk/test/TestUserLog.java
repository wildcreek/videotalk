package com.mateo.videotalk.test;

import com.mateo.videotalk.Constant;
import com.squareup.okhttp.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestUserLog {

    public static void main(String args[]) {
        testSaveLog();
    }

    private static void testSaveLog() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("userID", "testuserid");
        File file = new File("C:\\test.txt");
        builder.addFormDataPart("file", "test.txt", RequestBody.create(mediaType, file));
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(Constant.UPLOAD_LOG_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Request request, IOException e) {
                System.out.print("失败" + request.body().toString() + e.toString());
            }

            public void onResponse(Response response) throws IOException {
                System.out.print("成功" + response.body().string());
            }
        });
    }
}
