package com.chatbot.domain.zsxq.model.resp;

public class SubjectsRsp {
    private boolean succeeded;

    private RespData respData;

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getRespData() {
        return respData;
    }

    public void setRespData(RespData respData) {
        this.respData = respData;
    }
}
