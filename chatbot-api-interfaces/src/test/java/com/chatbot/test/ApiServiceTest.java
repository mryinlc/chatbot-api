package com.chatbot.test;

import com.chatbot.domain.openai.IOpenAiService;
import com.chatbot.domain.openai.model.resp.OpenAiResp;
import com.chatbot.domain.openai.model.vo.Choice;
import com.chatbot.domain.zsxq.IZsxqApi;
import com.chatbot.domain.zsxq.model.resp.CommentResp;
import com.chatbot.domain.zsxq.model.resp.SubjectsRsp;
import com.chatbot.domain.zsxq.model.vo.Topics;
import com.chatbot.domain.zsxq.service.ZsxqApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ApiServiceTest {

    @Value("${chatbot-api.userId}")
    private String userId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Value("${chatbot-api.apiKey}")
    private String apiKey;

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

    @Autowired
    private IOpenAiService aiService;

    @Test
    public void testOpenAiService() throws IOException {
        // System.out.println("aiService.getApiKey() = " + aiService.getApiKey());
        OpenAiResp aiResp = aiService.getAnswer("用java语言实现字符串逆转");
        for (Choice choice : aiResp.getChoices()) {
            System.out.println("choice.getText() = " + choice.getText());
        }
    }
}
