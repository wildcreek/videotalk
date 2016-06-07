package com.mateo.videotalk.controller;

import com.mateo.videotalk.model.response.BaseResponse;
import com.mateo.videotalk.service.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("/userlog")

public class UserLogController {

    private static Logger log = LoggerFactory.getLogger(UserLogController.class);
    private UserLogService userLogService;

    @Autowired
    public void setUserLogService(UserLogService userLogService) {
        this.userLogService = userLogService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> uploadLog(@RequestParam("userID") String userID,
            @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request) throws Exception {
        BaseResponse baseResponse;
        CommonsMultipartFile mFile;
        log.error("上传文件个数:" + files.length);
        for (int i = 0; i < files.length; i++) {
            mFile = files[i];
            if (!mFile.isEmpty()) {
                try {
                    log.error("上传文件名:" + mFile.getOriginalFilename());
                    String fileName = mFile.getOriginalFilename();
                    String path = request.getSession().getServletContext().getRealPath(File.separator + "resources" + File.separator + userID + File.separator + "logs");
                    File localFile = new File(path, fileName);
                    if (!localFile.exists()) {
                        localFile.mkdirs();
                    }
                    try {
                        mFile.transferTo(localFile);
                        userLogService.saveLog(userID, localFile.getAbsolutePath().replace("\\", "\\\\"));
                        log.error("上传成功，userID:" + userID + ",文件名:" + fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        baseResponse = new BaseResponse("success", "", "");
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }
}
