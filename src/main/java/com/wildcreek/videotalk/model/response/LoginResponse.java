package com.wildcreek.videotalk.model.response;

/**
 * Created by Administrator on 2016/5/28.
 */
public class LoginResponse extends BaseResponse {
    private LoginResult result;

    public LoginResponse() {
    }

    public LoginResult getResult() {
        return result;
    }

    public void setResult(LoginResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "result=" + result +
                '}';
    }

    public class LoginResult{
        private String userID;
        private String firstLogin;
        private String changeDevice;

        public LoginResult() {
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getFirstLogin() {
            return firstLogin;
        }

        public void setFirstLogin(String firstLogin) {
            this.firstLogin = firstLogin;
        }

        public String getChangeDevice() {
            return changeDevice;
        }

        public void setChangeDevice(String changeDevice) {
            this.changeDevice = changeDevice;
        }

        @Override
        public String toString() {
            return "LoginResult{" +
                    "userID='" + userID + '\'' +
                    ", firstLogin='" + firstLogin + '\'' +
                    ", changeDevice='" + changeDevice + '\'' +
                    '}';
        }
    }

}
