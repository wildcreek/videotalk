package com.wildcreek.videotalk.service.impl;

import com.wildcreek.videotalk.dao.UserDao;
import com.wildcreek.videotalk.model.User;
import com.wildcreek.videotalk.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    public User findUserByUserAccount(String userAccount) {
        User user = userDao.findUserByUserAccount(userAccount);
        return user;
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        User user = userDao.findUserByPhoneNumber(phoneNumber);
        return user;
    }

    public User findUserByUserID(String userID) {
        User user = userDao.findUserByUserID(userID);
        return user;
    }

    public String insertUser(User user) {
        String clientType = user.getClientType();
        long userID = userDao.getLatestUserIDByClientType(clientType) + 1;
        String userAccount = user.getUserAccount();
        String clientID = user.getClientID();
        String loginType = user.getLoginType();
        String phoneNumber = user.getPhoneNumber();
        String province = user.getProvince();
        String password = user.getPassword();
        boolean isSuccess = userDao.insertUser(userID, userAccount, clientType, clientID, loginType, phoneNumber, province, password);
        if (isSuccess) {
            return userID + "";
        }
        return "";
    }

    public boolean updateClientID(String userAccount, String clientID) {

        return userDao.updateClientID(userAccount, clientID);
    }

    public boolean updatePhoneNumberAndProvince(String userID, String phoneNumber, String province) {

        return userDao.updatePhoneNumberAndProvince(userID, phoneNumber, province);
    }


}
