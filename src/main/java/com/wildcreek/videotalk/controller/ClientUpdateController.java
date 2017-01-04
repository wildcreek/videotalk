package com.wildcreek.videotalk.controller;

import com.wildcreek.videotalk.config.ResultStatus;
import com.wildcreek.videotalk.model.ResultModel;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 */
@Controller
@RequestMapping("/update")

public class ClientUpdateController {

    private static Logger log = LoggerFactory.getLogger(ClientUpdateController.class);

    /**
     * 手机客户端升级信息获取
     */
    @RequestMapping(value = "/phone/info", method = RequestMethod.GET)
    public ResponseEntity<ResultModel> phoneUpdateInfo(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("phone_update_info.properties");
        try{
            HashMap<String, String> response = parseUpdateInfoFromConfig(inputStream);
            return new ResponseEntity<ResultModel>(ResultModel.ok(response), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.LOAD_UPDATE_CONFIGURE_FAILURE), HttpStatus.OK);
        }
    }
    /**
     * 机顶盒客户端升级信息获取
     */
    @RequestMapping(value = "/stb/info", method = RequestMethod.GET)
    public ResponseEntity<ResultModel> stbUpdateInfo(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("phone_update_info.properties");
        try{
            HashMap<String, String> response = parseUpdateInfoFromConfig(inputStream);
            return new ResponseEntity<ResultModel>(ResultModel.ok(response), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.LOAD_UPDATE_CONFIGURE_FAILURE), HttpStatus.OK);
        }
    }

    private HashMap<String, String> parseUpdateInfoFromConfig(InputStream inputStream) throws IOException {
        Properties p = new Properties();
        p.load(inputStream);
        HashMap<String, String> response = new HashMap<String, String>();
        response.put("apkname", p.getProperty("apkname"));
        response.put("versioncode", p.getProperty("versioncode"));
        response.put("url", p.getProperty("url"));
        response.put("description", p.getProperty("description"));
        response.put("forceupdate", p.getProperty("forceupdate"));
        response.put("versionName", p.getProperty("versionName"));
        return  response;
    }
    /**
     * 手机apk下载
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/phone/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadPhoneApk(){
        HttpHeaders headers = new HttpHeaders();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("phone_update_info.properties");
        byte[] apkBytes = new byte[0];
        try {
            apkBytes = getApkByteArrayFromConfigure(inputStream,headers);
            return new ResponseEntity<byte[]>(apkBytes,headers, HttpStatus.OK);
        } catch (IOException e1) {
            e1.printStackTrace();
            return new ResponseEntity<byte[]>(apkBytes,headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    /**
     * 机顶盒apk下载
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/stb/download",method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadStbApk() {
        HttpHeaders headers = new HttpHeaders();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("stb_update_info.properties");
        byte[] apkBytes = new byte[0];
        try {
            apkBytes = getApkByteArrayFromConfigure(inputStream,headers);
            return new ResponseEntity<byte[]>(apkBytes,headers, HttpStatus.OK);
        } catch (Exception e2) {
            e2.printStackTrace();
            return new ResponseEntity<byte[]>(apkBytes,headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private byte[] getApkByteArrayFromConfigure(InputStream inputStream,HttpHeaders headers) throws IOException {
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        headers.setContentDispositionFormData("attachment", p.getProperty("apkname"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String path=p.getProperty("Path");//apk所在的具体位置
        File file = new File(path);
        return  FileUtils.readFileToByteArray(file);
    }
}
