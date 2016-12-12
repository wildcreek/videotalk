package com.wildcreek.videotalk.model.response;

/**
 * Created by Administrator on 2016/5/28.
 */
public class FindNickNameResponse extends BaseResponse{
    private FindNickNameResult result;

    public FindNickNameResponse() {
    }

    public FindNickNameResult getResult() {
        return result;
    }

    public void setResult(FindNickNameResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "FindNickNameResponse{" +
                "result=" + result +
                '}';
    }

    public class FindNickNameResult{
        private String nickName;

        @Override
        public String toString() {
            return "FindNickNameResult{" +
                    "nickName='" + nickName + '\'' +
                    '}';
        }

        public FindNickNameResult() {
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }

}
