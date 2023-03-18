package com.chatbot.domain.zsxq;

import com.chatbot.domain.zsxq.model.resp.CommentResp;
import com.chatbot.domain.zsxq.model.resp.SubjectsRsp;

import java.io.IOException;

/**
 * 知识星球api接口
 */
public interface IZsxqApi {
    SubjectsRsp getSubjectsRepByUserId(String userId, String cookie) throws IOException;

    CommentResp addCommentByTopicId(String topicId, String cookie, String comment) throws IOException;
}
