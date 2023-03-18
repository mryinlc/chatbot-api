package com.chatbot.test;

import com.chatbot.domain.zsxq.IZsxqApi;
import com.chatbot.domain.zsxq.model.resp.CommentResp;
import com.chatbot.domain.zsxq.model.resp.SubjectsRsp;
import com.chatbot.domain.zsxq.model.vo.Topics;
import com.chatbot.domain.zsxq.service.ZsxqApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ApiServiceTest {

    @Value("${chatbot-api.userId}")
    private String userId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Test
    public void testApiService() throws IOException, InterruptedException {
        IZsxqApi api = new ZsxqApi();
        SubjectsRsp resp = api.getSubjectsRepByUserId(userId, cookie);
        Thread.sleep(2000);
        for (Topics topic : resp.getRespData().getTopics()) {
            String text = topic.getTalk().getText();
            CommentResp commentResp = api.addCommentByTopicId(topic.getTopicId(), cookie, text);
            System.out.println(text + ": " + (commentResp.isSucceeded() ? "回答成功" : "回答失败"));
            Thread.sleep(2000);
        }
    }
}
