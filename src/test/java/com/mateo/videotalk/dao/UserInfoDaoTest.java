package com.mateo.videotalk.dao;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/6/2.
 */
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
        String userID = "2000000001";
        String avatar = "C:\\avatars\\avatar3.jpg";
        boolean isSuccess = userInfoDao.updateAvatarByUserID(userID,avatar);
        System.out.println(isSuccess);
    }

    @Test
    public void updateNickNameByUserID() throws Exception {
        String userID = "2000000001";
        String nickName = "wangwu";
        boolean isSuccess = userInfoDao.updateNickNameByUserID(userID,nickName);
        System.out.println(isSuccess);
    }

}