//package com.wildcreek.videotalk.test;
//
//import com.squareup.okhttp.*;
//
//import java.io.IOException;
//
///**
// * Created by Administrator on 2016/5/28.
// */
//public class TestClientUpdate {
//
//    public static void main(String args[]) {
//        testGetPhoneUpdateInfo();
//        testGetStbUpdateInfo();
//        testDownloadPhoneApk();
//    }
//
//    private static void testGetPhoneUpdateInfo() {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(IseeUrl.PHONE_UPDATE_INFO_URL).build();
//        client.newCall(request).enqueue(new Callback() {
//
//            public void onFailure(Request request, IOException e) {
//                System.out.println("获取手机端升级信息失败" + request.body().toString() + e.toString());
//            }
//
//            public void onResponse(Response response) throws IOException {
//                System.out.println("获取手机端升级信息成功" + response.body().string());
//            }
//        });
//    }
//
//    private static void testGetStbUpdateInfo() {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(IseeUrl.STB_UPDATE_INFO_URL).build();
//        client.newCall(request).enqueue(new Callback() {
//
//            public void onFailure(Request request, IOException e) {
//                System.out.println("获取机顶盒端升级信息失败" + request.body().toString() + e.toString());
//            }
//
//            public void onResponse(Response response) throws IOException {
//                System.out.println("获取机顶盒端升级信息成功" + response.body().string());
//            }
//        });
//    }
//
//    private static void testDownloadPhoneApk() {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(IseeUrl.PHONE_DOWNLOAD_URL).build();
//        client.newCall(request).enqueue(new Callback() {
//
//            public void onFailure(Request request, IOException e) {
//                System.out.println("手机端下载失败" + request.body().toString() + e.toString());
//            }
//
//            public void onResponse(Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    System.out.println("手机端下载成功" + response.body().string());
//                } else {
//                    System.out.println("手机端下载失败 code:" + response.code() + ",response:" + response.body().string());
//                }
//            }
//        });
//    }
//}
