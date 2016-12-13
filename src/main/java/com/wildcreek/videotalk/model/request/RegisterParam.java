package com.wildcreek.videotalk.model.request;

/**
 *
 * Created by Administrator on 2016/5/28.
 *  clientType clientID userAccount loginType;
 */
public class RegisterParam {
    private String phoneNumber;
    private String password;
    private String smsCode;
    private String nickname;
    private String clientID;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "RegisterParam{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", nickname='" + nickname + '\'' +
                ", clientID='" + clientID + '\'' +
                '}';
    }
}
