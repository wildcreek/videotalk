package com.wildcreek.videotalk.service;

import com.wildcreek.videotalk.model.Contact;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserContactService {

    Contact findContact(int contactId , int userID);
    boolean isContactExist(Contact contact);
    Contact createContact(Contact contact);
    Contact updateContact(Contact contact);
    boolean deleteContact(int contactId,int userID);
    List<Contact> findAllContacts(int userID);

}
