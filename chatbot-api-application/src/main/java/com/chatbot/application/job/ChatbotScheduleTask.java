package com.chatbot.application.job;

import com.chatbot.domain.openai.IOpenAiService;
import com.chatbot.domain.openai.model.resp.OpenAiResp;
import com.chatbot.domain.zsxq.IZsxqApi;
import com.chatbot.domain.zsxq.model.resp.SubjectsRsp;
import com.chatbot.domain.zsxq.model.vo.Topics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

// @EnableScheduling
public class ChatbotScheduleTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(ChatbotScheduleTask.class);

    private final String apiKey;
    private final String userId;
    private final String cookie;
    private final IZsxqApi zsxqApi;
    private final IOpenAiService aiService;

    public ChatbotScheduleTask(String apiKey, String userId, String cookie, IZsxqApi zsxqApi, IOpenAiService aiService) {
        this.apiKey = apiKey;
        this.userId = userId;
        this.cookie = cookie;
        this.zsxqApi = zsxqApi;
        this.aiService = aiService;
    }

    @Override
    public void run() {
        if (new Random().nextBoolean()) {
            logger.info("AI临时休息，无法回答问题");
            return;
        }

        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > 22 || hour < 7) {
            logger.info("打烊时间不工作，AI下班了！");
            return;
        }

        try {
            SubjectsRsp resp = zsxqApi.getSubjectsRepByUserId(userId, cookie);
            for (Topics topic : resp.getRespData().getTopics()) {
                if (topic.getCommentsCount() == 0) {
                    // OpenAiResp aiResp = aiService.getAnswer(apiKey, topic.getTalk().getText());
                    OpenAiResp aiResp = aiService.getMockAnswer(topic.getTalk().getText());
                    String answer = aiResp.getChoices().get(0).getText();
                    zsxqApi.addCommentByTopicId(topic.getTopicId(), cookie, answer);
                    logger.info("AI回答了\"{}\"问题，答复为: {}", topic.getTalk().getText(), answer);
                    return;
                }
            }
            logger.info("暂时没有可回答的问题");
        } catch (IOException e) {
            logger.info("运行出错了，暂无法回答问题");
            e.printStackTrace();
            throw new RuntimeException("运行异常，请稍后再试");
        }
    }
}
