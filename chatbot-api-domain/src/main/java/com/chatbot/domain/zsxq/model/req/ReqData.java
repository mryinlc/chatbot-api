package com.chatbot.domain.zsxq.model.req;

import java.util.List;

public class ReqData {
    private String text;
    private List<String> imageIds;
    private List<String> mentionedUserIds;

    public ReqData() {
    }

    public ReqData(String text) {
        this.text = text;
        // this.imageIds = new ArrayList<>();
        // this.mentionedUserIds = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<String> imageIds) {
        this.imageIds = imageIds;
    }

    public List<String> getMentionedUserIds() {
        return mentionedUserIds;
    }

    public void setMentionedUserIds(List<String> mentionedUserIds) {
        this.mentionedUserIds = mentionedUserIds;
    }
}
