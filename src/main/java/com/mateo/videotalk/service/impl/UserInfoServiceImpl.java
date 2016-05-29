package com.mateo.videotalk.service.impl;

import com.mateo.videotalk.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    public String findAvatarByUserID(String userID){
        return "databaseAvatarPath";
    }
    public String findNickNameByUserID(String userID){
        return "";
    }
    public boolean updateAvatarByUserID(String userID){
        return true;
    }
    public boolean updateNickNameByUserID(String userID,String nickName){
        return true;
    }

}
