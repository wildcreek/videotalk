package com.wildcreek.videotalk.model;

public class Contact {
    private int contactId;
    private int userID;
    private String contactName;
    private String contactNumber;
    private String contactAvatar;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactAvatar() {
        return contactAvatar;
    }

    public void setContactAvatar(String contactAvatar) {
        this.contactAvatar = contactAvatar;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", userID=" + userID +
                ", contactName='" + contactName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contactAvatar='" + contactAvatar + '\'' +
                '}';
    }
}

	

