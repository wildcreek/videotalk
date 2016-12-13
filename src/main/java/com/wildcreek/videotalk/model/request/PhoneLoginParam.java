package com.wildcreek.videotalk.model.request;

/**
 *
 * Created by Administrator on 2016/5/28.
 *  clientType clientID userAccount loginType;
 */
public class PhoneLoginParam {
    private String clientID;
    private String userAccount;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PhoneLoginParam{" +
                "clientID='" + clientID + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
