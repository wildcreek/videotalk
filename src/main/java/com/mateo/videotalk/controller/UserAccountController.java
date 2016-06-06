package com.mateo.videotalk.controller;

import com.mateo.videotalk.model.User;
import com.mateo.videotalk.model.request.PhoneNumberParam;
import com.mateo.videotalk.model.response.CheckTokenResult;
import com.mateo.videotalk.model.response.LoginResponse;
import com.mateo.videotalk.model.response.PhoneNumberResponse;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class UserAccountController {

    private static Logger log = LoggerFactory.getLogger(UserAccountController.class);
    private UserService userService;
    HttpRequestor httpRequest = new HttpRequestor();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity<LoginResponse> loginUser(@RequestBody User user) {
//        @RequestParam(value = "clientID", required = true) String clientID,
//        @RequestParam(value = "clientType", required = true) String clientType,
//        @RequestParam(value = "userAccount", required = true) String userAccount,
//        @RequestParam(value = "loginType", required = true) String loginType
        log.debug("Info of loginParam(user):");
        log.debug(ReflectionToStringBuilder.toString(user));
        String clientID = user.getClientID();
        String clientType = user.getClientType();
        String userAccount = user.getUserAccount();
        String loginType = user.getLoginType();
        User resultUser = new User();
        resultUser.setClientID(clientID);
        resultUser.setClientType(clientType);
        resultUser.setLoginType(loginType);
        resultUser.setUserAccount(userAccount);
        //在此进行业务操作，比如数据库持久化
        //接收到注册请求,查询数据库，如已经注册，返回相应参数；未注册，则插入数据库并返回相应参数。
        LoginResponse loginResponse = new LoginResponse();
        LoginResponse.LoginResult loginResult = loginResponse.new LoginResult();
        User localUser = userService.findUserByUserAccount(userAccount);
        if (localUser != null) {//已经注册，返回相应参数,同时将用户信息插入数据库
            Long userID = localUser.getUserID();
            String localClientID = localUser.getClientID();
            loginResult.setUserID(userID + "");
            loginResult.setFirstLogin("false");
            if (clientID.equals(localClientID)) {
                loginResult.setChangeDevice("false");
            } else {
                loginResult.setChangeDevice("true");
            }
            userService.updateClientID(userAccount, clientID);
        } else {//未注册
            //TODO 生成userID，将相关数据插入数据库,此时userID为空
            //resultUser.setPhoneNumber(localUser.getPhoneNumber());
            // resultUser.setProvince(localUser.getProvince());
            boolean isSuccess = userService.insertUser(resultUser);
            if (isSuccess) {
                String userID = userService.findUserByUserAccount(userAccount).getUserID() + "";
                loginResult.setUserID(userID);
            }
            loginResult.setFirstLogin("true");
            loginResult.setChangeDevice("false");

        }
        loginResponse.setStatus("success");
        loginResponse.setErrorCode("");
        loginResponse.setErrorMsg("");
        loginResponse.setResult(loginResult);
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getNumber", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity<PhoneNumberResponse> getPhoneNumber(@RequestBody PhoneNumberParam params) {
        log.debug("Info of PhoneNumberParam:");
        log.debug(ReflectionToStringBuilder.toString(params));
        //重新封装参数，向认证平台请求校验token，获取手机号和省份等信息
        checkToken(params);
        PhoneNumberResponse result = checkToken(params);
        return new ResponseEntity<PhoneNumberResponse>(result, HttpStatus.OK);
    }

    private PhoneNumberResponse checkToken(PhoneNumberParam params) {
        String userID = params.getUserID();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String systemtime = sdf.format(new Date());
        Map<String, String> header = new HashMap<String, String>();
        Map<String, String> body = new HashMap<String, String>();
        Map<String, Map<String, String>> requestParam = new HashMap<String, Map<String, String>>();
        header.put("version", params.getVersion());
        header.put("msgid", params.getMsgid());
        header.put("systemtime", systemtime);
        header.put("sourceid", params.getSourceid());
        header.put("apptype", "5");//5 代表手机客户端
        header.put("appid", params.getAppid());
        body.put("token", params.getToken());
        requestParam.put("header", header);
        requestParam.put("body", body);
        PhoneNumberResponse phoneNumberResponse = new PhoneNumberResponse();
        PhoneNumberResponse.PhoneNumberResult phoneNumberResult = phoneNumberResponse.new PhoneNumberResult();
        try {
            String paramStr = JacksonUtil.beanToJson(requestParam);
            String result = httpRequest.doPost("http://wap.cmpassport.com:8080/api/tokenValidate", paramStr);
            CheckTokenResult checkTokenResult = (CheckTokenResult) JacksonUtil.jsonToBean(result, CheckTokenResult.class);
            String phoneNumber = checkTokenResult.getBody().getMsisdn();
            String province = checkTokenResult.getBody().getProvince();
            String errorCode = checkTokenResult.getHeader().getResultcode();
            phoneNumberResult.setUserID(userID);
            phoneNumberResponse.setErrorCode(errorCode);
            phoneNumberResult.setMsisdn(phoneNumber);//可能为空
            phoneNumberResult.setMsisdntype(checkTokenResult.getBody().getMsisdntype());
            phoneNumberResult.setProvince(province);
            userService.updatePhoneNumberAndProvince(userID,phoneNumber,province);
        } catch (Exception e) {
        }
        phoneNumberResponse.setStatus("success");
        phoneNumberResponse.setErrorMsg("");
        phoneNumberResponse.setResult(phoneNumberResult);
        return phoneNumberResponse;
    }

}
