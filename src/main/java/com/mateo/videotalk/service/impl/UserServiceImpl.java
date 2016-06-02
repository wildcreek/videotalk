package com.mateo.videotalk.service.impl;

import com.mateo.videotalk.dao.UserDao;
import com.mateo.videotalk.model.User;
import com.mateo.videotalk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static javafx.scene.input.KeyCode.R;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao ;
    public User findUserByUserAccount(String userAccount){
        User user = new User();
        user.setClientID("111");
        user.setUserID(10001L);
        user.setLoginType("1");
        user = userDao.findUserByUserAccount(userAccount);
        return user;
    }
    public boolean insertUser(User user){
        String clientType = user.getClientType();
        long userID = userDao.getLatestUserIDByClientType(clientType) + 1;
        String userAccount = user.getUserAccount();
        String clientID = user.getClientID();
        String loginType = user.getLoginType();
        String phoneNumber = user.getPhoneNumber();
        String province = user.getProvince();

        return userDao.insertUser(userID,userAccount,clientType,clientID,loginType,phoneNumber,province);
    }

    public boolean updateClientID(String userAccount, String clientID) {

        return userDao.updateClientID(userAccount,clientID);
    }

    public boolean updatePhoneNumberAndProvince(String userAccount, String phoneNumber, String province) {

        return userDao.updatePhoneNumberAndProvince(userAccount,phoneNumber,province);
    }


}
