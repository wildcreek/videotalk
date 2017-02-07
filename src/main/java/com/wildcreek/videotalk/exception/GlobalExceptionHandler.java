package com.wildcreek.videotalk.exception;

import com.wildcreek.videotalk.config.ResultStatus;
import com.wildcreek.videotalk.model.ResultModel;
import com.wildcreek.videotalk.util.StacktraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;

/*
 * Created by Administrator on 2017/2/7.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultModel handleException(HttpServletRequest request, Exception ex) {
        String content = StacktraceUtil.getStacktraceString(ex);
        log.error("GlobalExceptionHandler : " + ex.toString());
        return ResultModel.error(ResultStatus.UNEXPECTED_EXCEPTION ,content);
    }
}
