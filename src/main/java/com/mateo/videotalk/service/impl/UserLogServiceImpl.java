package com.mateo.videotalk.service.impl;

import com.mateo.videotalk.service.UserLogService;
import org.springframework.stereotype.Service;

@Service("userLogServie")
public class UserLogServiceImpl implements UserLogService{

    public boolean saveLog(String userid){
        //数据库操作
        return true;
    }
}
