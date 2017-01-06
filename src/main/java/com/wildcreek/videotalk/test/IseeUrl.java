package com.wildcreek.videotalk.test;

/**
 * Created by Administrator on 2016/5/28.
 */

public class IseeUrl {

    public static final String HTTP_HOST_PORT  = "http://192.168.1.133:8080";
    public static final String HTTPS_HOST_PORT = "https://192.168.1.133:8443";
    public static final String PHONE_LOGIN_URL         = HTTPS_HOST_PORT + "/videotalk/account/phone/login";
    public static final String PHONE_AUTH_LOGIN_URL    = HTTPS_HOST_PORT + "/videotalk/account/phone/auth_login";
    public static final String PHONE_REGISTER_URL      = HTTPS_HOST_PORT + "/videotalk/account/phone/register";
    public static final String PHONE_FORGET_PASS_URL   = HTTPS_HOST_PORT + "/videotalk/account/phone/modify_password";
    public static final String STB_AUTH_LOGIN_URL      = HTTPS_HOST_PORT + "/videotalk/account/stb/auth_login";
    public static final String SEND_SMS_URL            = HTTPS_HOST_PORT + "/videotalk/account/send_sms";
    public static final String LOGOUT_URL              = HTTPS_HOST_PORT + "/videotalk/account/logout";
    public static final String LEGACY_LOGIN_URL        = HTTPS_HOST_PORT + "/videotalk/account/login";

    public static final String UPLOAD_LOG_URL          = HTTP_HOST_PORT + "/videotalk/userlog/upload";
    public static final String FIND_NICKNAME_URL       = HTTP_HOST_PORT + "/videotalk/userinfo/nickname/find";
    public static final String CHANGE_NICKNAME_URL     = HTTP_HOST_PORT + "/videotalk/userinfo/nickname/update";
    public static final String FIND_AVATAR_URL         = HTTP_HOST_PORT + "/videotalk/userinfo/avatar/find";
    public static final String CHANGE_AVATAR_URL       = HTTP_HOST_PORT + "/videotalk/userinfo/avatar/update";
    public static final String CREATE_USER_CONTACT_URL = HTTP_HOST_PORT + "/videotalk/usercontact/create";
    public static final String UPDATE_USER_CONTACT_URL = HTTP_HOST_PORT + "/videotalk/usercontact/update";
    public static final String DELETE_USER_CONTACT_URL = HTTP_HOST_PORT + "/videotalk/usercontact/delete";
    public static final String FIND_ALL_CONTACT_URL    = HTTP_HOST_PORT + "/videotalk/usercontact/find_all";

    public static final String PHONE_UPDATE_INFO_URL   = HTTP_HOST_PORT + "/videotalk/update/phone/info";
    public static final String PHONE_DOWNLOAD_URL      = HTTP_HOST_PORT + "/videotalk/update/phone/download";
    public static final String STB_UPDATE_INFO_URL     = HTTP_HOST_PORT + "/videotalk/update/phone/info";
    public static final String STB_DOWNLOAD_URL        = HTTP_HOST_PORT + "/videotalk/update/phone/download";

}
