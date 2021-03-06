package com.wildcreek.videotalk.service.impl;

import com.wildcreek.videotalk.dao.UserLogDao;
import com.wildcreek.videotalk.service.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userLogServie")
public class UserLogServiceImpl implements UserLogService {
    private static Logger logger = LoggerFactory.getLogger(UserLogServiceImpl.class);
    @Autowired
    private UserLogDao userLogDao ;
    public boolean saveLog(String userID,String userLog){
        return userLogDao.saveLog(userID,userLog);
    }
}
