package com.mateo.videotalk.service;

import com.mateo.videotalk.model.Contact;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserContactService {

    /**
     * 查找单个联系人
     * @param userID
     * @param contactId
     * @return
     */
    Contact findContact(String userID,String contactId);

    /**联系人是否存在（查询数据不包含contactId）
     * @param contact
     * @return
     */
    boolean isContactExist(Contact contact);

    /**
     * 插入联系人
     * @param contact
     * @return
     */
    boolean insertContact(Contact contact);
    /**
     * 修改单个联系人
     * @return
     */
    Contact updateContact(Contact contact);

    /**
     * 删除单个联系人
     * @param contactId
     * @param userID
     * @return
     */
    boolean deleteContact(String contactId,String userID);

    /**
     * 查找指定账户的所有联系人
     * @param userID
     * @return
     */
    List<Contact> findAllContacts(String userID);

}
