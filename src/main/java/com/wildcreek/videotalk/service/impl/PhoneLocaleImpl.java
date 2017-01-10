package com.wildcreek.videotalk.service.impl;

import com.wildcreek.videotalk.dao.PhoneLocaleDao;
import com.wildcreek.videotalk.service.PhoneLocaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("phoneLocaleService")
public class PhoneLocaleImpl implements PhoneLocaleService{
    private static Logger logger = LoggerFactory.getLogger(PhoneLocaleImpl.class);
    @Autowired
    private PhoneLocaleDao phoneLocaleDao;

    public boolean insertIntoDb(int prefix, String province, String city, String provider, int areacode, int postcode) {
        return phoneLocaleDao.insertPhoneLocale(prefix, province, city, provider, areacode, postcode);
    }
}
