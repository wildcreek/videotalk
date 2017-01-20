package com.wildcreek.videotalk.model.response;

/**
 * Created by Administrator on 2017/1/20.
 */
public class LoginResponse {
    public String userID;
    public String phoneNumber;
    public String changeDevice;
    public String userToken;
    public String expireTime;

    public LoginResponse() {
    }

    public LoginResponse(String userID, String phoneNumber, String changeDevice, String userToken, String expireTime) {
        this.userID = userID;
        this.phoneNumber = phoneNumber;
        this.changeDevice = changeDevice;
        this.userToken = userToken;
        this.expireTime = expireTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getChangeDevice() {
        return changeDevice;
    }

    public void setChangeDevice(String changeDevice) {
        this.changeDevice = changeDevice;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "userID='" + userID + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", changeDevice='" + changeDevice + '\'' +
                ", userToken='" + userToken + '\'' +
                ", expireTime='" + expireTime + '\'' +
                '}';
    }
}
