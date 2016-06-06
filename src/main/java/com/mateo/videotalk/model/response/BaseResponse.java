package com.mateo.videotalk.model.response;

/**
 * Created by Administrator on 2016/6/6.
 */
public class BaseResponse {
    private String status;//success failure
    private String errorCode;//错误码
    private String errorMsg;//错误提示信息

    public BaseResponse() {
    }

    public BaseResponse(String status, String errorCode, String errorMsg) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
