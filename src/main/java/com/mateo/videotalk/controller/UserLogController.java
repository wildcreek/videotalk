package com.mateo.videotalk.controller;

import com.mateo.videotalk.service.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    public @ResponseBody Map<String, String> uploadLog(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
                    String path = request.getSession().getServletContext().getRealPath("logs");
                    File localFile = new File(path,fileName);
                    mFile.transferTo(localFile);
                    userLogService.saveLog(userID , localFile.getAbsolutePath().replace("\\","\\\\"));
                    //request.setAttribute("fileUrl", path);
                }
            }
        }
        HashMap<String,String> result = new HashMap<String, String>();
        result.put("userID",userID);
        return result;
    }

}
