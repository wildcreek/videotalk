package com.wildcreek.videotalk.dao;

import com.wildcreek.videotalk.model.User;
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
public class UserDaoTest {
    //注入Dao实现类
    @Resource
    private UserDao userDao;

    @Test
    public void findUserByUserAccount() throws Exception {
        String userAccount = "teststb1";
        User user = userDao.findUserByUserAccount(userAccount);
        System.out.println(user.toString());
    }

    @Test
    public void insertUser() throws Exception {
        long userID = userDao.getLatestUserIDByClientType("phone");
        User user = new User();
        user.setClientID("1009");
        user.setClientType("phone");
        user.setLoginType("1");
        user.setPhoneNumber("13555556666");
        user.setUserAccount("testphone3");
        user.setProvince("jiangsu");
        user.setUserID(userID);
        userDao.insertUser(userID, user.getUserAccount(), user.getClientType(), user.getClientID(), user.getLoginType(), user.getPhoneNumber(), user.getProvince());
    }

    @Test
    public void updateClientID() throws Exception {
        String userAccount = "test";
        String clientID = "1221";
        boolean isSuccess = userDao.updateClientID(userAccount, clientID);
        System.out.println(isSuccess);
    }

    @Test
    public void updatePhoneNumberAndProvince() throws Exception {
        String userAccount = "test";
        String phoneNumber = "18812345678";
        String province = "beijing";
        boolean isSuccess = userDao.updatePhoneNumberAndProvince(userAccount, phoneNumber, province);
        System.out.println(isSuccess);

    }

    @Test
    public void getLatestUserIDByClientType() throws Exception {
        String clientType = "phone";
        long userID = userDao.getLatestUserIDByClientType(clientType);
        System.out.println(userID);
    }

}