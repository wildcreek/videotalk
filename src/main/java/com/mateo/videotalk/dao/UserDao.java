package com.mateo.videotalk.dao;

import com.mateo.videotalk.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface UserDao {
    //多个方法参数可以使用类似@Param("userAccount")指明方法形参名，否则运行时会指定为如arg0等
    User findUserByUserAccount(@Param("userAccount")String userAccount);
    boolean insertUser(@Param("userAccount")String userAccount,@Param("clientType")String clientType,@Param("clientID") String clientID,
                       @Param("loginType")String loginType,@Param("phoneNumber")String phoneNumber,@Param("province")String province);
    boolean updateuser(@Param("userAccount")String userAccount,@Param("clientType")String clientType,@Param("clientID") String clientID,
                       @Param("loginType")String loginType,@Param("phoneNumber")String phoneNumber,@Param("province")String province);
    String getLatestUserIDByClientType(String clientType);
}
