package com.mateo.videotalk.service.impl;

import com.mateo.videotalk.model.Contact;
import com.mateo.videotalk.service.UserContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userContactService")
public class UserContactServiceImpl implements UserContactService {


    public Contact findContact(String userID, String contactId) {
        return null;
    }

    public boolean isContactExist(Contact contact) {
        return false;
    }

    public boolean insertContact(Contact contact) {
        return false;
    }

    public Contact updateContact(Contact contact) {
        Contact result = new Contact();
        result.setContactId("1");
        result.setContactName("2");
        result.setContactNumber("3");
        return contact;
    }

    public boolean deleteContact(String contactId, String userID) {
        return false;
    }

    public List<Contact> findAllContacts(String userID) {
        List<Contact> result = new ArrayList<Contact>();
        Contact contact1 = new Contact();
        contact1.setContactId("1");
        contact1.setContactName("2");
        contact1.setContactNumber("3");
        result.add(contact1);
        Contact contact2 = new Contact();
        contact2.setContactId("4");
        contact2.setContactName("5");
        contact2.setContactNumber("6");
        result.add(contact2);
        return result;
    }
}
