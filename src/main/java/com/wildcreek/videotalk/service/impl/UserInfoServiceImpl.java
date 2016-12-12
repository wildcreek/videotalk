package com.wildcreek.videotalk.service.impl;

import com.wildcreek.videotalk.dao.UserInfoDao;
import com.wildcreek.videotalk.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Autowired
    private UserInfoDao userInfoDao ;
    public String findAvatarByUserID(String userID){
        return userInfoDao.findAvatarByUserID(userID);
    }
    public String findNickNameByUserID(String userID){
        return userInfoDao.findNickNameByUserID(userID);
    }
    public boolean updateAvatarByUserID(String userID,String avatar){
        return userInfoDao.updateAvatarByUserID(userID,avatar);
    }
    public boolean updateNickNameByUserID(String userID,String nickName){
        return userInfoDao.updateNickNameByUserID(userID,nickName);
    }

}
