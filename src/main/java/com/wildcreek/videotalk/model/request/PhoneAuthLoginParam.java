package com.wildcreek.videotalk.model.request;

/**
 * Created by Administrator on 2016/5/28.
 */
public class PhoneAuthLoginParam {
    private String version;
    private String clientID;
    private String msgid;
    private String sourceid;
    private String appid;
    private String token;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    @Override
    public String toString() {
        return "PhoneAuthLoginParam{" +
                "version='" + version + '\'' +
                ", clientID='" + clientID + '\'' +
                ", msgid='" + msgid + '\'' +
                ", sourceid='" + sourceid + '\'' +
                ", appid='" + appid + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
