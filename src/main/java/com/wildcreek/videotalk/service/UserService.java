package com.wildcreek.videotalk.service;

import com.wildcreek.videotalk.model.User;
import org.springframework.stereotype.Service;
/*
* 业务接口:站在“使用者”的角度设计接口
* 三个方面:方法定义，参数，返回类型（包括异常）
* */
@Service
public interface UserService {

    User findUserByUserAccount(String userAccount);
    User findUserByPhoneNumber(String phoneNumber);
    User findUserByUserID(String userID);
    boolean insertUser(User user);
    boolean updateClientID(String userAccount,String clientID);
    boolean updatePhoneNumberAndProvince(String userID,String phoneNumber,String province);

}
