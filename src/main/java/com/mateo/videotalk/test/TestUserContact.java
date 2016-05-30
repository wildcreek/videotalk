package com.mateo.videotalk.test;

import com.mateo.videotalk.Constant;
import com.squareup.okhttp.*;
import com.squareup.okhttp.Request.Builder;

import java.io.IOException;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestUserContact {

    public static void main(String args[]) {
        testUpdateContact();
        testDeleteContact();
        testFindAllContacts();

    }

    public static void testUpdateContact() {
        OkHttpClient client = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("userID", "testUserID");
        builder.addFormDataPart("contactName", "testContactName");
        builder.addFormDataPart("contactNumber", "testContactNumber");
        RequestBody requestBody = builder.build();
        Request request = new Builder().url(Constant.UPDATE_USER_CONTACT_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Request request, IOException e) {
                System.out.println("更新联系人失败:" + request.body().toString() + e.toString());
            }

            public void onResponse(Response response) throws IOException {
                System.out.println("更新联系人成功:" + response.body().string());
            }
        });
    }

    public static void testDeleteContact() {
        OkHttpClient client = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("contactId", "testContactId");
        builder.addFormDataPart("userID", "testUserID");
        RequestBody requestBody = builder.build();
        Request request = new Builder().url(Constant.DELETE_USER_CONTACT_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Request request, IOException e) {
                System.out.println("删除联系人失败:" + request.body().toString() + e.toString());
            }

            public void onResponse(Response response) throws IOException {
                System.out.println("删除联系人成功:" + response.body().string());
            }
        });
    }

    public static void testFindAllContacts() {
        OkHttpClient client = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("userID", "testUserid");
        RequestBody requestBody = builder.build();
        Request request = new Builder().url(Constant.FIND_ALL_CONTACT_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Request request, IOException e) {
                System.out.println("获取所有联系人失败:" + request.body().toString() + e.toString());
            }

            public void onResponse(Response response) throws IOException {
                System.out.println("获取所有联系人成功:" + response.body().string() );
            }
        });
    }

}
