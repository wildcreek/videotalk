package com.mateo.videotalk.dao;

import com.mateo.videotalk.model.User;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface UserDao {
    User findUserByUserAccount(String userAccount);
}
