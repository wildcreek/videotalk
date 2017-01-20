//package com.wildcreek.videotalk.test;
//
//import com.alibaba.fastjson.JSONObject;
//import com.squareup.okhttp.*;
//
//import java.io.IOException;
//
///**
// * Created by Administrator on 2016/5/28.
// */
//public class TestNumberExist {
//
//    public static void main(String args[]) {
//        checkNum("94753677504");
//    }
//    public static void checkNum(String num) {
//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
//        JSONObject params = new JSONObject();
//        params.put("userID", num);
//        RequestBody requestBody = RequestBody.create(mediaType, params.toJSONString());
//        Request request = new Request.Builder().url("http://223.99.141.138:8080/videotalk/usercontact/phoneNumberExistFlag").post(requestBody).build();
//        client.newCall(request).enqueue(new Callback() {
//            public void onResponse(Response response) throws IOException {
//                System.out.println("号码是否存在"+response.body().string());
//
//            }
//
//            public void onFailure(Request arg0, IOException arg1) {
//
//            }
//        });
//    }
//
//}
