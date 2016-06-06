package com.mateo.videotalk.model.response;

/**
 * Created by Administrator on 2016/5/28.
 */
public class ContactResponse extends BaseResponse{
    private ContactResult result;

    public ContactResponse() {
    }

    public ContactResult getResult() {
        return result;
    }

    public void setResult(ContactResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "FindNickNameResponse{" +
                "result=" + result +
                '}';
    }

    public class ContactResult{
        private String contactId;

        public ContactResult() {
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }

        @Override
        public String toString() {
            return "ContactResult{" +
                    "contactId='" + contactId + '\'' +
                    '}';
        }
    }

}
