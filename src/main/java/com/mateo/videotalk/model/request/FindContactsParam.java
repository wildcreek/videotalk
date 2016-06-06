package com.mateo.videotalk.model.request;

/**
 *
 * Created by Administrator on 2016/5/28.
 *  clientType clientID userAccount loginType;
 */
public class FindContactsParam {
    private String userID;

    public FindContactsParam() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "FindContactsParam{" +
                "userID='" + userID + '\'' +
                '}';
    }
}

