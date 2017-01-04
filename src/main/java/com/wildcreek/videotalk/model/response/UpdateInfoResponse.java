package com.wildcreek.videotalk.model.response;

/**
 * Created by Administrator on 2016/5/28.
 */
public class UpdateInfoResponse extends BaseResponse {
    private VersionResult result;

    public UpdateInfoResponse() {
    }

    public VersionResult getResult() {
        return result;
    }

    public void setResult(VersionResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "VersionResponse{" +
                "result=" + result +
                '}';
    }

    public class VersionResult{
        private String apkname;
        private String  versioncode;
        private String url;
        private String description;
        private String forceupdate;
        private String versionName;

        public VersionResult() {
        }

        public String getApkname(){
            return this.apkname;
        }
        public void setApkname(String apkname){
            this.apkname=apkname;
        }
        public String getVersioncode(){
            return this.versioncode;
        }
        public void setVersioncode(String versioncode){
            this.versioncode=versioncode;
        }
        public String getUrl(){
            return this.url;
        }
        public void setUrl(String url){
            this.url=url;
        }
        public String getDescription(){
            return this.description;
        }
        public void setDescription(String description){
            this.description=description;
        }
        public String getForceupdate(){
            return this.forceupdate;
        }
        public void setForceupdate(String forceupdate){
            this.forceupdate=forceupdate;
        }
        public void setVersionName(String versionName){this.versionName = versionName;
        }
        public String getVersionName(){
            return this.versionName;
        }
        @Override
        public String toString() {
            return "VersionResult{" +
                    "apkname='" + apkname + '\'' +
                    ", versioncode='" + versioncode + '\'' +
                    ", url='" + url + '\'' +
                    ", description='" + description + '\'' +
                    ", forceupdate='" + forceupdate + '\'' +
                    ", versionName='" + versionName + '\'' +
                    '}';
        }
    }

}
