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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;

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
    public ResponseEntity<BaseResponse> uploadLog(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BaseResponse baseResponse ;
        String userID = "";
        request.setCharacterEncoding("UTF-8");
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(cmr.isMultipart(request)){
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)(request);
            userID =  mRequest.getParameter("userID");
            Iterator<String> files = mRequest.getFileNames();
            while(files.hasNext()){
                MultipartFile mFile = mRequest.getFile(files.next());
                if(mFile != null){
                    log.debug("In uploadLog, mFileName = {}", mFile.getOriginalFilename()  );
                    System.out.print("mFile:" + mFile.getOriginalFilename());
                    String fileName = mFile.getOriginalFilename() ;
                    String path = request.getSession().getServletContext().getRealPath(File.separator + "resources" + File.separator + userID + File.separator + "logs");
                    File localFile = new File(path,fileName);
                    if(!localFile.exists()){
                        localFile.mkdirs();
                    }
                    try {
                        mFile.transferTo(localFile);
                        userLogService.saveLog(userID , localFile.getAbsolutePath().replace("\\","\\\\"));
                        //request.setAttribute("fileUrl", path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        baseResponse = new BaseResponse("success","","");
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

}
