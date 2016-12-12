package com.wildcreek.videotalk.dao;

import com.wildcreek.videotalk.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserContactDaoTest {
    @Resource
    private UserContactDao userContactDao;
    @Test
    public void findContact() throws Exception {
        int contactId = 1;
        int userID = 2000000001;
        Contact contact = userContactDao.findContact(contactId,userID);
        System.out.println(contact.toString());
    }

    @Test
    public void insertContact() throws Exception {
        Contact contact = new Contact();
        int userID = 2000000001;
        String contactName = "testInsert";
        String contactNumber = "1212121";
        String contactAvatar = "testContactAvatar";
        contact.setUserID(userID);
        contact.setContactNumber(contactNumber);
        contact.setContactName(contactName);
        contact.setContactAvatar(contactAvatar);
        userContactDao.insertContact(contact);
        System.out.println("contactId = " + contact.getContactId());
    }

    @Test
    public void updateContact() throws Exception {
        int contactId = 1;
        int userID = 2000000001;
        String contactName = "testUpdate";
        String contactNumber = "1212121";
        String contactAvatar = "testContactAvatar";
        boolean  isSuccess = userContactDao.updateContact(contactId,userID,contactName,contactNumber,contactAvatar);
        System.out.println("updateContact ->" + isSuccess);
    }

    @Test
    public void deleteContact() throws Exception {
        int contactId = 2;
        int userID = 2000000001;
        boolean  isSuccess = userContactDao.deleteContact(contactId,userID);
        System.out.println("deleteContact ->" + isSuccess);
    }

    @Test
    public void findAllContacts() throws Exception {
        int userID = 2000000001;
        List<Contact> allContact =  userContactDao.findAllContacts(userID);
        System.out.println("findAllContacts ->" + Arrays.toString(allContact.toArray()));
    }
}