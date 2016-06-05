package com.mateo.videotalk.controller;

import com.mateo.videotalk.model.Contact;
import com.mateo.videotalk.service.UserContactService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        log.debug("Info of contact:");
        log.debug(ReflectionToStringBuilder.toString(contact));
        //插入数据库并返回相应参数。
        Contact resultContact = userContactService.createContact(contact);

        return new ResponseEntity<Contact>(resultContact, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        log.debug("Info of contact:");
        log.debug(ReflectionToStringBuilder.toString(contact));
        //接收到更新用户联系人请求。如联系人已经存在，则更新；不存在，则插入数据库并返回相应参数。
        //不存在则创建
        Contact resultContact = userContactService.updateContact(contact);

        return new ResponseEntity<Contact>(resultContact, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteContact(@RequestParam(value = "contactId", required = true) int contactId,
                                             @RequestParam(value = "userID", required = true) int userID) {
        System.out.println("contactId value: " + contactId + "userID vaule: " + userID);
        //数据库插入
        boolean isSuccess = userContactService.deleteContact(Integer.valueOf(contactId), Integer.valueOf(userID));
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("userID", userID + "");
        if (isSuccess) {
            result.put("isSuccess", "true");
        } else {
            result.put("isSuccess", "false");
        }
        return result;

    }

    @RequestMapping(value = "/find_all", method = RequestMethod.POST)
    public ResponseEntity<List<Contact>> findAvatarByUserID(@RequestParam(value = "userID", required = true) int userID) {
        System.out.println("userID value: " + userID);
        //数据库查询
        List<Contact> contacts = userContactService.findAllContacts(userID);
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

}
