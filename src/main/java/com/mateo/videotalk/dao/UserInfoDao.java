package com.mateo.videotalk.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/5/31.
 */
@Repository
public interface UserInfoDao {
    //多个方法参数可以使用类似@Param("userAccount")指明方法形参名，否则运行时会指定为如arg0等
    String findAvatarByUserID(@Param("userID") String userID);
    boolean updateAvatarByUserID(@Param("userID") String userID,@Param("avatar") String avatar);
    String findNickNameByUserID(@Param("userID")String userID);
    boolean updateNickNameByUserID(@Param("userID")String userID,@Param("nickName")String nickName);
}
