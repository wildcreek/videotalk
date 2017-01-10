package com.wildcreek.videotalk.controller;

import com.wildcreek.videotalk.config.ResultStatus;
import com.wildcreek.videotalk.model.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 */
@Controller
@RequestMapping("/recommend")

public class ClientRecommendController {

    private static Logger log = LoggerFactory.getLogger(ClientRecommendController.class);

    /**
     * 推荐购买摄像头的文字描述信息
     */
    @RequestMapping(value = "/buycamera", method = RequestMethod.GET)
    public ResponseEntity<ResultModel> phoneUpdateInfo(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("client_recommend_info.properties");
        Properties p = new Properties();
        HashMap<String, String> response = new HashMap<String, String>();
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.LOAD_CONFIGURATION_FAILURE), HttpStatus.OK);
        }
        response.put("buyInfo1",p.getProperty("buyInfo1"));
        response.put("buyInfo2",p.getProperty("buyInfo2"));
        response.put("buyInfo3",p.getProperty("buyInfo3"));
        return new ResponseEntity<ResultModel>(ResultModel.ok(response), HttpStatus.OK);
    }
    /**
     * 推荐下载手机端app的文字描述信息
     */
    @RequestMapping(value = "/downloadphone", method = RequestMethod.GET)
    public ResponseEntity<ResultModel> stbUpdateInfo(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("client_recommend_info.properties");
        Properties p = new Properties();
        HashMap<String, String> response = new HashMap<String, String>();
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.LOAD_CONFIGURATION_FAILURE), HttpStatus.OK);
        }
        response.put("downloadInfo1",p.getProperty("downloadInfo1"));
        response.put("downloadInfo2",p.getProperty("downloadInfo2"));
        response.put("downloadInfo3",p.getProperty("downloadInfo3"));
        response.put("downloadInfo4",p.getProperty("downloadInfo4"));
        response.put("downloadInfo5",p.getProperty("downloadInfo5"));
        response.put("downloadInfo6",p.getProperty("downloadInfo6"));
        response.put("downloadInfo7",p.getProperty("downloadInfo7"));
        return new ResponseEntity<ResultModel>(ResultModel.ok(response), HttpStatus.OK);

    }

}
