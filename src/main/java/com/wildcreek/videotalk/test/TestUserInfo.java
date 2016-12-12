package com.wildcreek.videotalk.test;

import com.wildcreek.videotalk.Constant;
import com.squareup.okhttp.*;
import com.squareup.okhttp.Request.Builder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/5/28.
 */
public class TestUserInfo {

    public static void main(String args[]) {
        testUpdateAvatar();
        testGetAvatar();

    }

    public static void testUpdateAvatar() {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("multipart/form-data; charset=utf-8");
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("userID", "2000000001");
        File file = new File("C:\\test.txt");
        builder.addFormDataPart("file", "test.txt", RequestBody.create(mediaType, file));
        RequestBody requestBody = builder.build();
        Request request = new Builder().url(Constant.CHANGE_AVATAR_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Request request, IOException e) {
                System.out.println("上传头像失败:" + request.body().toString() + e.toString());
            }

            public void onResponse(Response response) throws IOException {
                System.out.println("上传头像成功:" + response.body().string());
            }
        });
    }

    public static void testGetAvatar() {
        OkHttpClient client = new OkHttpClient();
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        builder.addFormDataPart("userID", "2000000002");
        RequestBody requestBody = builder.build();
        Request request = new Builder().url(Constant.FIND_AVATAR_URL).post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Request request, IOException e) {
                System.out.println("获取头像失败:" + request.body().toString() + e.toString());
            }

            public void onResponse(Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                String fileName = "default";
                try {
                    String attachment = response.header("Content-Disposition");
                    //注意：此处返回的值为 Content-Disposition: form-data; name="attachment"; filename="avatar.jpg"
                    //即filename带双引号
                    fileName = attachment.split("filename=")[1].replace("\"","");
                } catch (Exception e) {

                }
                System.out.println("获取头像成功,文件名:" +  fileName);
                writeStreamToFile(inputStream,fileName);
            }
        });
    }

    public static void writeStreamToFile(InputStream is,String fileName) {
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        File avatarRoot = new File("C:\\avatars");
        try {
            File file = new File(avatarRoot , fileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
        } catch (IOException e) {
            System.out.println("保存头像失败 :" +  e.toString());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
            }
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
            }
        }

    }
}
