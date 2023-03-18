package com.chatbot.domain.zsxq.model.req;

public class CommentReq {
    private ReqData reqData;

    public CommentReq(ReqData reqData) {
        this.reqData = reqData;
    }

    public ReqData getReqData() {
        return reqData;
    }

    public void setReqData(ReqData reqData) {
        this.reqData = reqData;
    }
}
