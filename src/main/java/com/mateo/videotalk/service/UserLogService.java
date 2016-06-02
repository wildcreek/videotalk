package com.mateo.videotalk.service;

import org.springframework.stereotype.Service;

@Service
public interface UserLogService {
    boolean saveLog(String userid,String userLog);
}
