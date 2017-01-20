package com.wildcreek.videotalk.model;

import com.wildcreek.videotalk.config.ResultStatus;

/**
 * 自定义返回结果
 * @author XieEnlong
 * @date 2015/7/14.
 */
public class ResultModelTest<T> {

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

    public Object getContent() {
        return content;
    }

    public ResultModelTest(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModelTest(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResultModelTest(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResultModelTest(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public static ResultModelTest ok(Object content) {
        return new ResultModelTest(ResultStatus.SUCCESS, content);
    }

    public static ResultModelTest ok() {
        return new ResultModelTest(ResultStatus.SUCCESS);
    }

    public static ResultModelTest error(ResultStatus error) {
        return new ResultModelTest(error);
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
