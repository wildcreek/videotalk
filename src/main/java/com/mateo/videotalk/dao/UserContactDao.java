package com.mateo.videotalk.dao;

import com.mateo.videotalk.model.Contact;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
@Repository
public interface UserContactDao {
    Contact findContact(@Param("contactId")int contactId ,@Param("userID")int userID);
    int insertContact(Contact contact);

    boolean updateContact(@Param("contactId")int contactId, @Param("userID")int userID, @Param("contactName")String contactName,
                          @Param("contactNumber")String contactNumber, @Param("contactAvatar")String contactAvatar);

    boolean deleteContact(@Param("contactId")int contactId, @Param("userID")int userID);

    List<Contact> findAllContacts(@Param("userID")int userID);
}
