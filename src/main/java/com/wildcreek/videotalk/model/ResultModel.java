package com.wildcreek.videotalk.model;

import com.wildcreek.videotalk.config.ResultStatus;

/**
 * 自定义返回结果
 * @author XieEnlong
 * @date 2015/7/14.
 */
public class ResultModel<T> {

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回内容
     */
    private T content;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getContent() {
        return content;
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResultModel(ResultStatus status, T content) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    public static ResultModel ok() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
