package com.mateo.videotalk.model.response;

import com.mateo.videotalk.model.Contact;

import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public class FindAllContactResponse extends BaseResponse{
    private List<Contact> result;

    public FindAllContactResponse() {

    }

    public List<Contact> getResult() {
        return result;
    }

    public void setResult(List<Contact> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "FindAllContactResponse{" +
                "result=" + result +
                '}';
    }
}
