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
    /**
     * 客户端认证平台获取的唯一账号
     **/
    private String userAccount;
    /**
     * 终端设备类型
     **/
    private String clientType;
    /**
     * 登录的终端ID
     **/
    private String clientID;
    /**
     * 客户端登录认证方式
     **/
    private String loginType;
    /**
     * 平台自动生成的10位或11位数字，表示用户ID
     **/
    private Long userID;
    /**
     * 平台自动生成的10位或11位数字，表示用户ID
     **/
    private String password;
    /**
     * 手机号码
     **/
    private String phoneNumber;
    /**
     * 省份
     **/
    private String province;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userAccount='" + userAccount + '\'' +
                ", clientType='" + clientType + '\'' +
                ", clientID='" + clientID + '\'' +
                ", loginType='" + loginType + '\'' +
                ", userID=" + userID +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
}

	

