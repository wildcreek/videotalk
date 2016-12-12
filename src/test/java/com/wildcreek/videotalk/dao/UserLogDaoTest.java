package com.wildcreek.videotalk.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/6/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserLogDaoTest {
    @Resource
    private UserLogDao userLogDao;
    @Test
    public void saveLog() throws Exception {
        String userID = "2000000001";
        String userLog = "C:\\\\Users\\\\Administrator\\\\Desktop\\\\多屏交互事件.txt";
        boolean isSuccess = userLogDao.saveLog(userID,userLog);
        System.out.println(isSuccess);
;    }
}