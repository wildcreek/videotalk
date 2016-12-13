package com.wildcreek.videotalk.controller;

import com.wildcreek.videotalk.authorization.annotation.Authorization;
import com.wildcreek.videotalk.authorization.annotation.CurrentUser;
import com.wildcreek.videotalk.authorization.manager.TokenManager;
import com.wildcreek.videotalk.authorization.model.TokenModel;
import com.wildcreek.videotalk.config.ResultStatus;
import com.wildcreek.videotalk.model.ResultModel;
import com.wildcreek.videotalk.model.User;
import com.wildcreek.videotalk.model.request.PhoneAuthLoginParam;
import com.wildcreek.videotalk.model.request.PhoneLoginParam;
import com.wildcreek.videotalk.model.response.CheckTokenResult;
import com.wildcreek.videotalk.model.response.LoginResponse;
import com.wildcreek.videotalk.service.UserService;
import com.wildcreek.videotalk.util.HttpRequestor;
import com.wildcreek.videotalk.util.JacksonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 */
@Controller
@RequestMapping("/session")
public class UserSessionController {
    private static Logger log = LoggerFactory.getLogger(UserSessionController.class);
    HttpRequestor httpRequest = new HttpRequestor();
    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value = "/phone_login", method = RequestMethod.POST)
    public ResponseEntity<ResultModel> login(@RequestBody PhoneLoginParam params) {
        String userAccount = params.getUserAccount();
        String password = params.getPassword();
        User user = userService.findUserByUserAccount(userAccount);
        log.debug("UserSessionController" + ReflectionToStringBuilder.toString(user));
        if (user == null || !password.equals(user.getPassword())) {  //提示用户名或密码错误
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        TokenModel model = tokenManager.createToken(user.getUserID());
        HashMap<String, String> response = new HashMap<String, String>();
        response.put("userID", user.getUserID() + "");
        response.put("phoneNumber", userAccount);
        response.put("userToken", model.getToken());
        response.put("expireTime", "72");

        return new ResponseEntity<ResultModel>(ResultModel.ok(response), HttpStatus.OK);
    }

    @RequestMapping(value = "/phone_auth_login", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity<ResultModel> authLogin(@RequestBody PhoneAuthLoginParam params) {
        log.debug("Info of PhoneAuthLoginParam:");
        log.debug(ReflectionToStringBuilder.toString(params));

        ResultModel result = checkToken(params);

        return new ResponseEntity<ResultModel>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/stb_auth_login", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity<LoginResponse> loginUser(@RequestBody User user) {
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

    private ResultModel checkToken(PhoneAuthLoginParam params) {

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
        try {
            String paramStr = JacksonUtil.beanToJson(requestParam);
            log.error("校验token请求信息：" + paramStr);
            String result = httpRequest.doPost("http://wap.cmpassport.com:8080/api/tokenValidate", paramStr);
            log.error("校验token返回信息：" + result);
            CheckTokenResult checkTokenResult = (CheckTokenResult) JacksonUtil.jsonToBean(result, CheckTokenResult.class);
            String phoneNumber = checkTokenResult.getBody().getMsisdn();
            String province = checkTokenResult.getBody().getProvince();
            String errorCode = checkTokenResult.getHeader().getResultcode();
            if (phoneNumber != null && !phoneNumber.equals("null")){//校验token成功
                User localUser = userService.findUserByPhoneNumber(phoneNumber);
                if (localUser == null) {//不存在,直接插入
                    User resultUser = new User(phoneNumber,"phone",params.getClientID(),"0","");
                    boolean isSuccess = userService.insertUser(resultUser);
                    if(isSuccess){//插入新用户成功
                        return ResultModel.ok();
                    }else{//插入新用户失败
                        return ResultModel.error(ResultStatus.USER_CREATE_FAILURE);
                    }
                }
            }else{//校验token失败

            }
            if (StringUtils.isEmpty(province) && !StringUtils.isEmpty(phoneNumber)) {
                String xmlResult = httpRequest.doGet("http://life.tenpay.com/cgi-bin/mobile/MobileQueryAttribution.cgi?chgmobile=" + phoneNumber);
                province = parseXmlResult(xmlResult);
            }
            //userService.updatePhoneNumberAndProvince(userID, phoneNumber, province);
        } catch (Exception e) {//校验token失败
            log.error("获取手机号码异常:" + e.toString());
            e.printStackTrace();
        }
        return ResultModel.ok();
    }

    private String parseXmlResult(String xmlResult) {
        String province = "";
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new ByteArrayInputStream(xmlResult.getBytes("GBK")));
            Element root = doc.getRootElement();
            province = root.element("province").getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return province;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @Authorization
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getUserID());
        return new ResponseEntity<ResultModel>(ResultModel.ok(), HttpStatus.OK);

    }

}
