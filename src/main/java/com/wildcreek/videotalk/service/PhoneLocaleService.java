package com.wildcreek.videotalk.service;

import org.springframework.stereotype.Service;

@Service
public interface PhoneLocaleService {
    boolean insertIntoDb(int prefix, String province, String city, String provider, int areacode, int postcode) ;
}
