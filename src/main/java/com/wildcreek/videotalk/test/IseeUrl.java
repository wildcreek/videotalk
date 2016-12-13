package com.wildcreek.videotalk.test;

/**
 * Created by Administrator on 2016/5/28.
 */

public class IseeUrl {
    //public static final String HOST = "http://192.168.1.184:8080/";
    public static final String HOST = "http://192.168.1.133:8080/";
    public static final String PHONE_LOGIN_URL         = HOST + "/session/phone_login";
    public static final String PHONE_AUTH_LOGIN_URL    = HOST + "/session/phone_auth_login";
    public static final String USER_LOGOUT_URL         = HOST + "/session/logout";

    public static final String USER_REGISTER_URL       = HOST + "/account/register";
    public static final String USER_LOGIN_URL          = HOST + "/account/login";
    public static final String GET_PHONE_NUMBER_URL    = HOST + "/account/getNumber";
    public static final String UPLOAD_LOG_URL          = HOST + "/userlog/upload";
    public static final String FIND_NICKNAME_URL       = HOST + "/userinfo/nickname/find";
    public static final String CHANGE_NICKNAME_URL     = HOST + "/userinfo/nickname/update";
    public static final String FIND_AVATAR_URL         = HOST + "/userinfo/avatar/find";
    public static final String CHANGE_AVATAR_URL       = HOST + "/userinfo/avatar/update";
    public static final String CREATE_USER_CONTACT_URL = HOST + "/usercontact/create";
    public static final String UPDATE_USER_CONTACT_URL = HOST + "/usercontact/update";
    public static final String DELETE_USER_CONTACT_URL = HOST + "/usercontact/delete";
    public static final String FIND_ALL_CONTACT_URL    = HOST + "/usercontact/find_all";
}
