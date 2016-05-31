package com.mateo.videotalk.controller;

import com.mateo.videotalk.model.User;
import com.mateo.videotalk.model.request.PhoneNumberParam;
import com.mateo.videotalk.model.response.CheckTokenResult;
import com.mateo.videotalk.model.response.LoginResult;
import com.mateo.videotalk.model.response.PhoneNumberResult;
import com.mateo.videotalk.service.UserService;
import com.mateo.videotalk.util.HttpRequestor;
import com.mateo.videotalk.util.JacksonUtil;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
// /courses/**
public class UserAccountController {

    private static Logger log = LoggerFactory.getLogger(UserAccountController.class);
    private UserService userService;
    HttpRequestor httpRequest = new HttpRequestor();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method=RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity<LoginResult> loginUser(@ModelAttribute User user) {
        log.debug("Info of User:");
        log.debug(ReflectionToStringBuilder.toString(user));
        //在此进行业务操作，比如数据库持久化
        //接收到注册请求,查询数据库，如已经注册，返回相应参数；未注册，则插入数据库并返回相应参数。
        LoginResult loginResponse = new LoginResult();
        User localUser = userService.findUserByUserAccount(user.getUserAccount());
        String clientID = localUser.getClientID();
        if(localUser != null){//已经注册，返回相应参数
            Long userID = localUser.getUserID();
            loginResponse.setUserID(userID + "");
            loginResponse.setFirstLogin("false");
            if (clientID.equals(localUser.getClientID())) {
                loginResponse.setChangeDevice("false");
            } else {
                loginResponse.setChangeDevice("true");
            }
        }else{//未注册
            //TODO 生成userid，将相关数据插入数据库,此时userid为空
            loginResponse.setUserID("1000000001");
            loginResponse.setFirstLogin("true");
            loginResponse.setChangeDevice("false");

        }
        return new ResponseEntity<LoginResult>(loginResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getNumber", method=RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity<PhoneNumberResult> getPhoneNumber(@ModelAttribute PhoneNumberParam params) {
        log.debug("Info of PhoneNumberParam:");
        log.debug(ReflectionToStringBuilder.toString(params));
        //重新封装参数，向认证平台请求校验token，获取手机号和省份等信息
        checkToken(params);
        PhoneNumberResult result =  checkToken(params);
        return new ResponseEntity<PhoneNumberResult>(result, HttpStatus.OK);
    }

    private PhoneNumberResult checkToken(PhoneNumberParam params){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String systemtime = sdf.format(new Date());
        Map<String, String> header = new HashMap<String, String>();
        Map<String, String> body = new HashMap<String, String>();
        Map<String, Map<String, String>> requestParam = new HashMap<String, Map<String, String>>();
        header.put("version", params.getVersion());
        header.put("msgid", params.getMsgid());
        header.put("systemtime", systemtime);
        header.put("sourceid", params.getSourceid());
        header.put("apptype", "phone");//TODO
        header.put("appid", params.getAppid());
        body.put("token", params.getToken());
        requestParam.put("header", header);
        requestParam.put("body", body);
        PhoneNumberResult phoneNumberResult = new PhoneNumberResult();
        try{
            String paramStr = JacksonUtil.beanToJson(requestParam);
            String result = httpRequest.doPost("http://wap.cmpassport.com:8080/api/tokenValidate", paramStr);
            CheckTokenResult checkTokenResult = (CheckTokenResult)JacksonUtil.jsonToBean(result, CheckTokenResult.class);
            phoneNumberResult.setInresponseto(checkTokenResult.getHeader().getInresponseto());
            phoneNumberResult.setResultcode( checkTokenResult.getHeader().getResultcode());
            phoneNumberResult.setMsisdn( checkTokenResult.getBody().getMsisdn());//可能为空
            phoneNumberResult.setMsisdntype( checkTokenResult.getBody().getMsisdntype());
        }catch (Exception e){
        }
        return  phoneNumberResult;
    }

}
