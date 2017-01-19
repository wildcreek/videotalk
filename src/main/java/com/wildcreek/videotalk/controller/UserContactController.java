package com.wildcreek.videotalk.controller;

import com.wildcreek.videotalk.model.Contact;
import com.wildcreek.videotalk.model.request.DeleteContactParam;
import com.wildcreek.videotalk.model.request.FindContactsParam;
import com.wildcreek.videotalk.model.response.BaseResponse;
import com.wildcreek.videotalk.model.response.ContactResponse;
import com.wildcreek.videotalk.model.response.FindAllContactResponse;
import com.wildcreek.videotalk.service.UserContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/usercontact")
public class UserContactController {

    private static Logger log = LoggerFactory.getLogger(UserContactController.class);
    private UserContactService userContactService;

    @Autowired
    public void setUserContactService(UserContactService userContactService) {
        this.userContactService = userContactService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ContactResponse> createContact(@RequestParam(value = "userID", required = true) String userID ,
                                                         @RequestParam(value = "contactName", required = true) String contactName ,
                                                         @RequestParam(value = "contactNumber", required = true) String contactNumber ,
                                                         @RequestParam(value = "file", required = true) MultipartFile file , HttpServletRequest request) {
        log.debug("Info of contact:");
        Contact contact = new Contact();
        contact.setUserID(Integer.valueOf(userID));
        contact.setContactName(contactName);
        contact.setContactNumber(contactNumber);
        contact.setContactAvatar(file.getOriginalFilename());

        //生成服务端文件保存路径
        String path = request.getSession().getServletContext().getRealPath(File.separator + "resources" + File.separator + userID + File.separator + "avatars");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        System.out.println("通讯录头像保存路径:" + targetFile.getAbsolutePath());
        //通讯录头像保存至本地
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ContactResponse response = new ContactResponse();
        ContactResponse.ContactResult result = response.new ContactResult();
        //插入数据库并返回相应参数
        Contact resultContact = userContactService.createContact(contact);

        result.setContactId(resultContact.getContactId() + "");
        response.setCode("1000");
        response.setMsg("");
        response.setResult(result);
        return new ResponseEntity<ContactResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> updateContact(@RequestParam(value = "contactId", required = true) String contactId ,
                                                      @RequestParam(value = "userID", required = true) String userID ,
                                                      @RequestParam(value = "contactName", required = true) String contactName ,
                                                      @RequestParam(value = "contactNumber", required = true) String contactNumber ,
                                                      @RequestParam(value = "file", required = true) MultipartFile file , HttpServletRequest request) {
        log.debug("Info of contact:");
        Contact contact = new Contact();
        contact.setContactId(Integer.valueOf(contactId));
        contact.setUserID(Integer.valueOf(userID));
        contact.setContactName(contactName);
        contact.setContactNumber(contactNumber);
        contact.setContactAvatar(file.getOriginalFilename());

        //生成服务端文件保存路径
        String path = request.getSession().getServletContext().getRealPath(File.separator + "resources" + File.separator + userID + File.separator + "avatars");
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        System.out.println("通讯录头像保存路径:" + targetFile.getAbsolutePath());
        //通讯录头像保存至本地
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        BaseResponse baseResponse ;
        //接收到更新用户联系人请求。如联系人已经存在，则更新；不存在，则插入数据库并返回相应参数。
        //不存在则创建
        Contact resultContact = userContactService.updateContact(contact);
        baseResponse = new BaseResponse("success","","");
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity<BaseResponse> deleteContact(@RequestBody DeleteContactParam param) {
        String contactId = param.getContactId();
        String userID  = param.getUserID();
        System.out.println("contactId value: " + contactId + "userID vaule: " + userID);
        BaseResponse baseResponse ;
        //数据库插入
        boolean isSuccess = userContactService.deleteContact(Integer.valueOf(contactId), Integer.valueOf(userID));
        if (isSuccess) {
            baseResponse = new BaseResponse("success","","");
        } else {
            baseResponse = new BaseResponse("failure","","数据库删除失败");
        }
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "/find_all", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public ResponseEntity<FindAllContactResponse> findAllContactsByUserID(@RequestBody FindContactsParam param) {
        int userID = Integer.valueOf(param.getUserID());
        System.out.println("userID value: " + userID);
        FindAllContactResponse response = new FindAllContactResponse();
        //数据库查询
        List<Contact> contacts = userContactService.findAllContacts(userID);
        String avatar;
        for (Contact contact: contacts) {
            avatar =  "http://localhost:8080/resources/" + userID + "/avatars/" + contact.getContactAvatar();
            contact.setContactAvatar(avatar);
        }
        response.setCode("1000");
        response.setMsg("");
        response.setResult(contacts);
        return new ResponseEntity<FindAllContactResponse>(response, HttpStatus.OK);
    }

}
