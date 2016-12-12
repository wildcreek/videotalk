package com.wildcreek.videotalk.dao;

import com.wildcreek.videotalk.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/5/31.
 */
@Repository
public interface UserDao {
    //多个方法参数可以使用类似@Param("userAccount")指明方法形参名，否则运行时会指定为如arg0等
    User findUserByUserAccount(@Param("userAccount")String userAccount);
    User findUserByUserID(@Param("userID")String userID);
    boolean insertUser(@Param("userID")long userID ,@Param("userAccount")String userAccount,@Param("clientType")String clientType,@Param("clientID") String clientID,
                       @Param("loginType")String loginType,@Param("phoneNumber")String phoneNumber,@Param("province")String province);
    boolean updateClientID(@Param("userAccount")String userAccount,@Param("clientID") String clientID );
    boolean updatePhoneNumberAndProvince(@Param("userID")String userID,@Param("phoneNumber")String phoneNumber,@Param("province")String province);
    long getLatestUserIDByClientType(String clientType);
}
