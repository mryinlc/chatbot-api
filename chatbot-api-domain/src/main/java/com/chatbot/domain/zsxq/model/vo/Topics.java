package com.chatbot.domain.zsxq.model.vo;

public class Topics {
    private String topicId;

    private Group group;

    private String type;

    private Talk talk;

    private int likesCount;

    private int rewardsCount;

    private int commentsCount;

    private int readingCount;

    private int readersCount;

    private boolean digested;

    private boolean sticky;

    private String createTime;

    private UserSpecific userSpecific;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setTalk(Talk talk) {
        this.talk = talk;
    }

    public Talk getTalk() {
        return this.talk;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getLikesCount() {
        return this.likesCount;
    }

    public void setRewardsCount(int rewardsCount) {
        this.rewardsCount = rewardsCount;
    }

    public int getRewardsCount() {
        return this.rewardsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getCommentsCount() {
        return this.commentsCount;
    }

    public void setReadingCount(int readingCount) {
        this.readingCount = readingCount;
    }

    public int getReadingCount() {
        return this.readingCount;
    }

    public void setReadersCount(int readersCount) {
        this.readersCount = readersCount;
    }

    public int getReadersCount() {
        return this.readersCount;
    }

    public void setDigested(boolean digested) {
        this.digested = digested;
    }

    public boolean getDigested() {
        return this.digested;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public boolean getSticky() {
        return this.sticky;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setUserSpecific(UserSpecific userSpecific) {
        this.userSpecific = userSpecific;
    }

    public UserSpecific getUserSpecific() {
        return this.userSpecific;
    }
}