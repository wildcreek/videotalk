package com.wildcreek.videotalk.test;

/**
 * Created by Administrator on 2016/5/28.
 */

public class IseeUrl {

    public static final String HTTP_HOST_PORT  = "http://192.168.1.133:8080";
    public static final String HTTPS_HOST_PORT = "https://192.168.1.133:8443";
    public static final String PHONE_LOGIN_URL         = HTTPS_HOST_PORT + "/account/phone/login";
    public static final String PHONE_AUTH_LOGIN_URL    = HTTPS_HOST_PORT + "/account/phone/auth_login";
    public static final String PHONE_REGISTER_URL      = HTTPS_HOST_PORT + "/account/phone/register";
    public static final String PHONE_FORGET_PASS_URL   = HTTPS_HOST_PORT + "/account/phone/forget_password";
    public static final String STB_AUTH_LOGIN_URL      = HTTPS_HOST_PORT + "/account/stb/auth_login";
    public static final String SEND_SMS_URL            = HTTPS_HOST_PORT + "/account/send_sms";
    public static final String LOGOUT_URL              = HTTPS_HOST_PORT + "/account/logout";
    public static final String LEGACY_LOGIN_URL        = HTTPS_HOST_PORT + "/account/login";

    public static final String UPLOAD_LOG_URL          = HTTP_HOST_PORT + "/userlog/upload";
    public static final String FIND_NICKNAME_URL       = HTTP_HOST_PORT + "/userinfo/nickname/find";
    public static final String CHANGE_NICKNAME_URL     = HTTP_HOST_PORT + "/userinfo/nickname/update";
    public static final String FIND_AVATAR_URL         = HTTP_HOST_PORT + "/userinfo/avatar/find";
    public static final String CHANGE_AVATAR_URL       = HTTP_HOST_PORT + "/userinfo/avatar/update";
    public static final String CREATE_USER_CONTACT_URL = HTTP_HOST_PORT + "/usercontact/create";
    public static final String UPDATE_USER_CONTACT_URL = HTTP_HOST_PORT + "/usercontact/update";
    public static final String DELETE_USER_CONTACT_URL = HTTP_HOST_PORT + "/usercontact/delete";
    public static final String FIND_ALL_CONTACT_URL    = HTTP_HOST_PORT + "/usercontact/find_all";
}
