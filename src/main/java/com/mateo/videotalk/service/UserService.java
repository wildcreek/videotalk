package com.mateo.videotalk.service;

import com.mateo.videotalk.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserByUserAccount(String userAccount);
    boolean insertUser(User user);
    boolean updateUser(User user);

}
