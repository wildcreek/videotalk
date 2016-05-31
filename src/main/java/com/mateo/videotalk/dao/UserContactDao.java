package com.mateo.videotalk.dao;

import com.mateo.videotalk.model.Contact;

/**
 * Created by Administrator on 2016/5/31.
 */
public interface UserContactDao {
    Contact queryById(String contactId);

}
