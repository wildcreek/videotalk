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
public class UserInfoDaoTest {
    @Resource
    private UserInfoDao userInfoDao;
    @Test
    public void findAvatarByUserID() throws Exception {
        String userID = "2000000001";
        String avatar = userInfoDao.findAvatarByUserID(userID);
        System.out.println(avatar);
    }

    @Test
    public void findNickNameByUserID() throws Exception {
        String userID = "2000000001";
        String nickName = userInfoDao.findNickNameByUserID(userID);
        System.out.println(nickName);
    }

    @Test
    public void updateAvatarByUserID() throws Exception {
        String userID = "2000000005";
        String avatar = "C:\\\\avatars\\\\avatar3.jpg";
        boolean isSuccess = userInfoDao.updateAvatarByUserID(userID,avatar);
        System.out.println(isSuccess);
    }

    @Test
    public void updateNickNameByUserID() throws Exception {
        String userID = "2000000005";
        String nickName = "zhaoliu";
        boolean isSuccess = userInfoDao.updateNickNameByUserID(userID,nickName);
        System.out.println(isSuccess);
    }

}