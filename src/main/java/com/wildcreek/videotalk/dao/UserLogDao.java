package com.wildcreek.videotalk.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/5/31.
 */
@Repository
public interface UserLogDao {
    //多个方法参数可以使用类似@Param("userAccount")指明方法形参名，否则运行时会指定为如arg0等
    boolean saveLog(@Param("userID")String userID,@Param("userLog") String userLog);
}
