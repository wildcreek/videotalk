package com.wildcreek.videotalk.service.impl;

import com.wildcreek.videotalk.dao.UserContactDao;
import com.wildcreek.videotalk.model.Contact;
import com.wildcreek.videotalk.service.UserContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userContactService")
public class UserContactServiceImpl implements UserContactService {

    private static Logger logger = LoggerFactory.getLogger(UserContactServiceImpl.class);
    @Autowired
    private UserContactDao userContactDao;

    public Contact findContact(int contactId, int userID) {

        return userContactDao.findContact(contactId,userID);
    }

    public boolean isContactExist(Contact contact) {
        return false;
    }

    public Contact createContact(Contact contact) {
        userContactDao.insertContact(contact);
        return contact;
    }

    public Contact updateContact(Contact contact) {
        int contactId = contact.getContactId();
        int userID =  contact.getUserID();
        String contactName = contact.getContactName();
        String contactNumber = contact.getContactNumber();
        String contactAvatar = contact.getContactAvatar();
        //TODO 更新失败判断
        boolean isSuccess = userContactDao.updateContact(contactId,userID,contactName,contactNumber,contactAvatar);
        return contact;
    }

    public boolean deleteContact(int contactId, int userID) {
        return userContactDao.deleteContact(contactId,userID);
    }

    public List<Contact> findAllContacts(int userID) {
        return userContactDao.findAllContacts(userID);
    }
}
