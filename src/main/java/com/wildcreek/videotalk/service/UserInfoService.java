package com.wildcreek.videotalk.service;

import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    String findAvatarByUserID(String userID);
    String findNickNameByUserID(String userID);
    boolean updateAvatarByUserID(String userID ,String avatar );
    boolean updateNickNameByUserID(String userID ,String nickName);

}
