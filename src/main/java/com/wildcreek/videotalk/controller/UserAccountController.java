package com.wildcreek.videotalk.controller;

import com.wildcreek.videotalk.config.ResultStatus;
import com.wildcreek.videotalk.model.ResultModel;
import com.wildcreek.videotalk.model.User;
import com.wildcreek.videotalk.model.request.RegisterParam;
import com.wildcreek.videotalk.model.response.BaseResponse;
import com.wildcreek.videotalk.model.response.FindNickNameResponse;
import com.wildcreek.videotalk.service.UserInfoService;
import com.wildcreek.videotalk.service.UserService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/account")
public class UserAccountController {

    private static Logger log = LoggerFactory.getLogger(UserAccountController.class);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<ResultModel> register(@RequestBody RegisterParam params) {
        String phoneNumber = params.getPhoneNumber();
        String password = params.getPassword();
        String smsCode = params.getSmsCode();
        String nickname = params.getNickname();//TODO
        String clientId = params.getClientID();
        if (verifySmsCode(smsCode)) {//短信验证码校验成功
            User localUser = userService.findUserByPhoneNumber(phoneNumber);
            if (localUser != null) {//已经存在，返回错误信息
                return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.USER_ALREADY_EXISTED),HttpStatus.OK);
            }else{//不存在,直接插入
                User resultUser = new User(phoneNumber,"phone",clientId,"0",password);
                boolean isSuccess = userService.insertUser(resultUser);
                if(isSuccess){//插入新用户成功
                    return new ResponseEntity<ResultModel>(ResultModel.ok(),HttpStatus.OK);
                }else{//插入新用户失败
                    return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.USER_CREATE_FAILURE),HttpStatus.OK);
                }
            }
        } else {//短信验证码校验失败
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.SMSCODE_VERIFY_FAILURE),HttpStatus.OK);
        }
    }

    private boolean verifySmsCode(String smsCode) {
        return true;
    }

    @RequestMapping(value = "/nickname/find", method = RequestMethod.POST)
    public ResponseEntity<FindNickNameResponse> findNickNameByUserID(@RequestParam(value = "userID", required = true) String userID) {
        FindNickNameResponse findNickNameResponse = new FindNickNameResponse();
        FindNickNameResponse.FindNickNameResult findNickNameResult = findNickNameResponse.new FindNickNameResult();
        System.out.println("userID value: " + userID);
        //数据库查询
        String nickName = userInfoService.findNickNameByUserID(userID);
        findNickNameResult.setNickName(nickName);
        findNickNameResponse.setStatus("success");
        findNickNameResponse.setErrorCode("");
        findNickNameResponse.setErrorMsg("");
        findNickNameResponse.setResult(findNickNameResult);
        return new ResponseEntity<FindNickNameResponse>(findNickNameResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/nickname/update", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> updateNickName(@RequestParam(value = "userID", required = true) String userID,
                                                       @RequestParam(value = "nickName", required = true) String nickName) {
        System.out.println("userID value: " + userID + "nickName vaule: " + nickName);
        BaseResponse baseResponse;
        //数据库插入
        boolean isSuccess = userInfoService.updateNickNameByUserID(userID, nickName);
        baseResponse = new BaseResponse("success", "", "");
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/avatar/find", method = RequestMethod.POST)
    public ResponseEntity<byte[]> findAvatarByUserID(@RequestParam(value = "userID", required = true) String userID) throws IOException {
        System.out.println("userID value: " + userID);
        //数据库查询
        String avatarPath = userInfoService.findAvatarByUserID(userID);
        //根据头像地址取出图片
        File file = new File(avatarPath);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(file.getName().getBytes("utf-8"), "ISO8859-1"));
        if (file.exists()) {//文件存在
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        } else {//TODO 文件不存在
            return new ResponseEntity<byte[]>(null, headers, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/avatar/update", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> updateAvatarByUserID(@RequestParam(value = "userID", required = true) String userID,
                                                             @RequestParam(value = "file", required = true) MultipartFile file, HttpServletRequest request) throws IOException {
        BaseResponse baseResponse;
        //生成服务端文件保存地址
        String path = request.getSession().getServletContext().getRealPath(File.separator + "resources" + File.separator + userID + File.separator + "avatars");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        System.out.println("服务端头像保存路径:" + targetFile.getAbsolutePath());
        boolean isSuccess = false;
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
            //在服务端保存文件地址时，分割符由\修正为\\
            isSuccess = userInfoService.updateAvatarByUserID(userID, targetFile.getAbsolutePath().replace("\\", "\\\\"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseResponse = new BaseResponse("success", "", "");
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

}
