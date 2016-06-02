package com.mateo.videotalk.dao;

import com.mateo.videotalk.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/5/31.
 */
@Repository
public interface UserDao {
    //多个方法参数可以使用类似@Param("userAccount")指明方法形参名，否则运行时会指定为如arg0等
    User findUserByUserAccount(@Param("userAccount")String userAccount);
    boolean insertUser(@Param("userID")long userID ,@Param("userAccount")String userAccount,@Param("clientType")String clientType,@Param("clientID") String clientID,
                       @Param("loginType")String loginType,@Param("phoneNumber")String phoneNumber,@Param("province")String province);
    boolean updateClientID(@Param("userAccount")String userAccount,@Param("clientID") String clientID );
    boolean updatePhoneNumberAndProvince(@Param("userAccount")String userAccount,@Param("phoneNumber")String phoneNumber,@Param("province")String province);
    long getLatestUserIDByClientType(String clientType);
}
