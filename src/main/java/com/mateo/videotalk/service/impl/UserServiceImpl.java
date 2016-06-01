package com.mateo.videotalk.service.impl;

import com.mateo.videotalk.dao.UserDao;
import com.mateo.videotalk.model.User;
import com.mateo.videotalk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserDao userDao;
    public User findUserByUserAccount(String userAccount){
        User user = new User();
        user = userDao.findUserByUserAccount(userAccount);
        return user;
    }
    public boolean insertUser(User user){
        String userAccount = user.getUserAccount();
        String clientID = user.getClientID();
        String clientType = user.getClientType();
        String loginType = user.getLoginType();
        String phoneNumber = user.getPhoneNumber();
        String province = user.getProvince();
        long userID = user.getUserID();
        userDao.insertUser(userAccount,clientType,clientID,loginType,phoneNumber,phoneNumber,province);
        return true;
    }
    public boolean updateUser(User user){

        return true;
    }

}
