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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Contact> updateContact(@ModelAttribute Contact contact) {
        log.debug("Info of contact:");
        log.debug(ReflectionToStringBuilder.toString(contact));
        //接收到更新用户联系人请求。如联系人已经存在，则更新；不存在，则插入数据库并返回相应参数。
        //不存在则创建
        Contact resultContact = userContactService.updateContact(contact);

        return new ResponseEntity<Contact>(resultContact, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteContact(@RequestParam(value = "contactId", required = true) String contactId,
                                             @RequestParam(value = "userID", required = true) String userID) {
        System.out.println("contactId value: " + contactId + "userID vaule: " + userID);
        //数据库插入
        boolean isSuccess = userContactService.deleteContact(contactId, userID);
        if (isSuccess) {

        } else {

        }
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("userID", userID);
        return result;

    }

    @RequestMapping(value = "/find_all", method = RequestMethod.POST)
    public ResponseEntity<List<Contact>> findAvatarByUserID(@RequestParam(value = "userID", required = true) String userID) {
        System.out.println("userID value: " + userID);
        //数据库查询
        List<Contact> contacts = userContactService.findContacts(userID);
        return new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
    }

}
