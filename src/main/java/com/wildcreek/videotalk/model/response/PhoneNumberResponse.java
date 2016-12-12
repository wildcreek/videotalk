package com.wildcreek.videotalk.model.response;

/**
 * Created by Administrator on 2016/5/28.
 */
public class PhoneNumberResponse extends BaseResponse{
    private PhoneNumberResult result;

    public PhoneNumberResponse() {
    }

    @Override
    public String toString() {
        return "PhoneNumberResponse{" +
                "result=" + result +
                '}';
    }

    public PhoneNumberResult getResult() {
        return result;
    }

    public void setResult(PhoneNumberResult result) {
        this.result = result;
    }

    public class PhoneNumberResult{
        private String userID;
        private String msisdn;
        private String msisdntype;
        private String province;

        public PhoneNumberResult() {
        }

        @Override
        public String toString() {
            return "PhoneNumberResult{" +
                    "userID='" + userID + '\'' +
                    ", msisdn='" + msisdn + '\'' +
                    ", msisdntype='" + msisdntype + '\'' +
                    ", province='" + province + '\'' +
                    '}';
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

        public String getMsisdntype() {
            return msisdntype;
        }

        public void setMsisdntype(String msisdntype) {
            this.msisdntype = msisdntype;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
