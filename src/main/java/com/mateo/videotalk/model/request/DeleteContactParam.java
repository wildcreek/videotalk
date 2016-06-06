package com.mateo.videotalk.model.request;

/**
 *
 * Created by Administrator on 2016/5/28.
 *  clientType clientID userAccount loginType;
 */
public class DeleteContactParam {
    private String contactId;
    private String userID;

    public DeleteContactParam() {
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "DeleteContactParam{" +
                "contactId='" + contactId + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}

