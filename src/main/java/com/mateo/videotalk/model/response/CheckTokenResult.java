package com.mateo.videotalk.model.response;

/**
 * Created by Administrator on 2016/5/28.
 */
public class CheckTokenResult {
    private Header header;
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public class Body {

        private String usessionid;
        private String passid;
        private String andid;
        private String msisdn;
        private String email;
        private String loginidtype;
        private String msisdntype;
        private String province;
        private String authtype;
        private String authtime;
        private String lastactivetime;
        private String relateToAndPassport;


        public String getUsessionid() {
            return usessionid;
        }

        public void setUsessionid(String usessionid) {
            this.usessionid = usessionid;
        }

        public String getPassid() {
            return passid;
        }

        public void setPassid(String passid) {
            this.passid = passid;
        }

        public String getAndid() {
            return andid;
        }

        public void setAndid(String andid) {
            this.andid = andid;
        }

        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLoginidtype() {
            return loginidtype;
        }

        public void setLoginidtype(String loginidtype) {
            this.loginidtype = loginidtype;
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

        public String getAuthtype() {
            return authtype;
        }

        public void setAuthtype(String authtype) {
            this.authtype = authtype;
        }

        public String getAuthtime() {
            return authtime;
        }

        public void setAuthtime(String authtime) {
            this.authtime = authtime;
        }

        public String getLastactivetime() {
            return lastactivetime;
        }

        public void setLastactivetime(String lastactivetime) {
            this.lastactivetime = lastactivetime;
        }

        public String getRelateToAndPassport() {
            return relateToAndPassport;
        }

        public void setRelateToAndPassport(String relateToAndPassport) {
            this.relateToAndPassport = relateToAndPassport;
        }
    }

    public class Header {

        private String version;
        private String inresponseto;
        private String resultcode;
        private String systemtime;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
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

        public String getSystemtime() {
            return systemtime;
        }

        public void setSystemtime(String systemtime) {
            this.systemtime = systemtime;
        }

    }

}
