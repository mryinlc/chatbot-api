package com.chatbot.domain.zsxq.model.vo;

public class Owner {
    private String userId;

    private String name;

    private String avatarUrl;

    private String location;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }
}
