package com.mateo.videotalk.model;

/***
 * @包名 com.cmcc.family.talk.entity
 * @类名 User.java
 * @描述 用户登录信息实体
 * @作者 wangchao
 * @时间 2016年5月17日 上午9:53:02
 * @版本
 * @参数
 */
public class User {

    private String userAccount;
    /**
     * 客户端认证平台获取的唯一账号
     **/
    private String clientType;
    /**
     * 终端设备类型
     **/
    private String clientID;
    /**
     * 登录的终端ID
     **/
    private String loginType;
    /**
     * 客户端登录认证方式
     **/
    private Long userID;

    /**
     * 平台自动生成的10位或11位数字，表示用户ID
     **/
    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

}

	

