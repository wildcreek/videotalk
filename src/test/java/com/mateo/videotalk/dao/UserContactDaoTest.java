package com.mateo.videotalk.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * Created by Administrator on 2016/5/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit srping 配置文件
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class UserContactDaoTest {
    //注入Dao实现依赖
    @Resource
    private UserContactDao userContactDao;
    @Test
    public void queryById() throws Exception {


    }

}