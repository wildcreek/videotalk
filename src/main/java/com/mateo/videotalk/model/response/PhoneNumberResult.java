package com.mateo.videotalk.model.response;

/**
 * Created by Administrator on 2016/5/28.
 */
public class PhoneNumberResult {
    private String inresponseto;
    private String resultcode;
    private String msisdn;
    private String msisdntype;
    private String number;
    private String province;

    public String getMsisdntype() {
        return msisdntype;
    }

    public void setMsisdntype(String msisdntype) {
        this.msisdntype = msisdntype;
    }

    public String getInresponseto() {
        return inresponseto;
    }

    public void setInresponseto(String inresponseto) {
        this.inresponseto = inresponseto;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
