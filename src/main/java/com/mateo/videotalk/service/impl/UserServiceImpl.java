package com.mateo.videotalk.service.impl;

import com.mateo.videotalk.model.User;
import com.mateo.videotalk.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    public User findUserByUserAccount(String userAccount){
        User user = new User();
        user.setUserID(1000000002L);
        user.setClientID("123");
        return user;
    }
    public boolean insertUser(User user){

        return true;
    }
    public boolean updateUser(User user){

        return true;
    }

}
