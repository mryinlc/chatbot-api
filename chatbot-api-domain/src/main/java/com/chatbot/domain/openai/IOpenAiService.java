package com.chatbot.domain.openai;

import com.chatbot.domain.openai.model.resp.OpenAiResp;

import java.io.IOException;

public interface IOpenAiService {
    OpenAiResp getAnswer(String question) throws IOException;
}
