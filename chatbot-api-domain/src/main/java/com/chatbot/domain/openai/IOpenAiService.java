package com.chatbot.domain.openai;

import com.chatbot.domain.openai.model.resp.OpenAiResp;

import java.io.IOException;

public interface IOpenAiService {
    OpenAiResp getAnswer(String apiKey, String question) throws IOException;

    OpenAiResp getMockAnswer(String question);
}
