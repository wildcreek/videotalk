package com.wildcreek.videotalk.model.request;

/**
 *
 * Created by Administrator on 2016/5/28.
 *  clientType clientID userAccount loginType;
 */
public class LoginParam {
    private String clientType;
    private String clientID;
    private String userAccount;
    private String loginType;

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

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "LoginParam{" +
                "clientType='" + clientType + '\'' +
                ", clientID='" + clientID + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", loginType='" + loginType + '\'' +
                '}';
    }
}
