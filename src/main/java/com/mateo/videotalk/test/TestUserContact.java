package com.mateo.videotalk.test;

import com.mateo.videotalk.Constant;
import com.squareup.okhttp.*;
import com.squareup.okhttp.Request.Builder;

import java.io.File;
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
        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("userID", "2000000008");
        builder.addFormDataPart("contactName", "heheh");
        builder.addFormDataPart("contactNumber", "hhha");
        File file = new File("C:\\avatar1.jpg");
        builder.addFormDataPart("file", "avatar1.jpg", RequestBody.create(mediaType, file));
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
        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("contactId", "8");
        builder.addFormDataPart("userID", "2000000008");
        builder.addFormDataPart("contactName", "666666");
        builder.addFormDataPart("contactNumber", "666666");
        File file = new File("C:\\avatar2.jpg");
        builder.addFormDataPart("file", "avatar2.jpg", RequestBody.create(mediaType, file));
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
        builder.addFormDataPart("userID", "2000000008");
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
        builder.addFormDataPart("userID", "2000000008");
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
