package com.mateo.videotalk.service;

import com.mateo.videotalk.model.User;
import org.springframework.stereotype.Service;
/*
* 业务接口:站在“使用者”的角度设计接口
* 三个方面:方法定义，参数，返回类型（包括异常）
* */
@Service
public interface UserService {

    User findUserByUserAccount(String userAccount);
    boolean insertUser(User user);
    boolean updateClientID(String userAccount,String clientID);
    boolean updatePhoneNumberAndProvince(String userAccount,String phoneNumber,String province);

}
