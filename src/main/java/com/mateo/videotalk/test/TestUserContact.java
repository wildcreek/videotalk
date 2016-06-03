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
        testCreateContact();
        testUpdateContact();
        testDeleteContact();
        testFindAllContacts();
    }

    public static void testCreateContact() {
        OkHttpClient client = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("userID", "2000000001");
        builder.addFormDataPart("contactName", "okhttpCreatedContactName");
        builder.addFormDataPart("contactNumber", "okhttpCreatedContactNumber");
        builder.addFormDataPart("contactAvatar", "okhttpCreatedContactAvatar");
        RequestBody requestBody = builder.build();
        Request request = new Builder().url(Constant.CREATE_USER_CONTACT_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Request request, IOException e) {
                System.out.println("添加联系人失败:" + request.body().toString() + e.toString());
            }

            public void onResponse(Response response) throws IOException {
                System.out.println("添加联系人成功:" + response.body().string());
            }
        });
    }

    public static void testUpdateContact() {
        OkHttpClient client = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("contactId", "8");
        builder.addFormDataPart("userID", "2000000001");
        builder.addFormDataPart("contactName", "testContactName");
        builder.addFormDataPart("contactNumber", "testContactNumber");
        builder.addFormDataPart("contactAvatar", "testContactAvatar");
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
        builder.addFormDataPart("contactId", "13");
        builder.addFormDataPart("userID", "2000000001");
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
        builder.addFormDataPart("userID", "2000000001");
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
