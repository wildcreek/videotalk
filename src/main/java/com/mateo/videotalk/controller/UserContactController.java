package com.mateo.videotalk.controller;

import com.mateo.videotalk.model.Contact;
import com.mateo.videotalk.model.response.BaseResponse;
import com.mateo.videotalk.model.response.ContactResponse;
import com.mateo.videotalk.service.UserContactService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ContactResponse> createContact(@RequestBody Contact contact) {
        log.debug("Info of contact:");
        log.debug(ReflectionToStringBuilder.toString(contact));
        ContactResponse response = new ContactResponse();
        ContactResponse.ContactResult result = response.new ContactResult();
        //插入数据库并返回相应参数。
        Contact resultContact = userContactService.createContact(contact);

        result.setContactId(resultContact.getContactId() + "");
        response.setStatus("success");
        response.setErrorCode("");
        response.setErrorMsg("");
        response.setResult(result);
        return new ResponseEntity<ContactResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> updateContact(@RequestBody Contact contact) {
        log.debug("Info of contact:");
        log.debug(ReflectionToStringBuilder.toString(contact));
        BaseResponse baseResponse ;
        //接收到更新用户联系人请求。如联系人已经存在，则更新；不存在，则插入数据库并返回相应参数。
        //不存在则创建
        Contact resultContact = userContactService.updateContact(contact);
        baseResponse = new BaseResponse("success","","");
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> deleteContact(@RequestParam(value = "contactId", required = true) int contactId,
                                             @RequestParam(value = "userID", required = true) int userID) {
        System.out.println("contactId value: " + contactId + "userID vaule: " + userID);
        BaseResponse baseResponse ;
        //数据库插入
        boolean isSuccess = userContactService.deleteContact(Integer.valueOf(contactId), Integer.valueOf(userID));
        if (isSuccess) {
            baseResponse = new BaseResponse("success","","");
        } else {
            baseResponse = new BaseResponse("failure","","插入数据库失败");
        }
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "/find_all", method = RequestMethod.POST)
    public ResponseEntity<List<Contact>> findAvatarByUserID(@RequestParam(value = "userID", required = true) int userID) {
        System.out.println("userID value: " + userID);
        //数据库查询
        List<Contact> contacts = userContactService.findAllContacts(userID);
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

}
