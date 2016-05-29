package com.mateo.videotalk.service;

import com.mateo.videotalk.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {

    String findAvatarByUserID(String userID);
    String findNickNameByUserID(String userID);
    boolean updateAvatarByUserID(String userID );
    boolean updateNickNameByUserID(String userID ,String nickName);

}
