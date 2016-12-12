package com.wildcreek.videotalk.controller;

import com.wildcreek.videotalk.authorization.annotation.Authorization;
import com.wildcreek.videotalk.authorization.annotation.CurrentUser;
import com.wildcreek.videotalk.authorization.manager.TokenManager;
import com.wildcreek.videotalk.authorization.model.TokenModel;
import com.wildcreek.videotalk.config.ResultStatus;
import com.wildcreek.videotalk.model.ResultModel;
import com.wildcreek.videotalk.model.User;
import com.wildcreek.videotalk.service.UserService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
 */
@Controller
@RequestMapping("/tokens")
public class UserSessionController {
    private static Logger log = LoggerFactory.getLogger(UserSessionController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<ResultModel> login(@RequestParam String userID, @RequestParam String password) {
        Assert.notNull(userID, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        User user = userService.findUserByUserID(userID);
        log.debug("TokenController" + ReflectionToStringBuilder.toString(user));
        if (user == null ||  //未注册
                !user.getPassword().equals(password)) {  //密码错误
            //提示用户名或密码错误
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        TokenModel model = tokenManager.createToken(user.getUserID());
        return new ResponseEntity<ResultModel>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @Authorization
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        tokenManager.deleteToken(user.getUserID());
        return new ResponseEntity<ResultModel>(ResultModel.ok(), HttpStatus.OK);

    }

}
