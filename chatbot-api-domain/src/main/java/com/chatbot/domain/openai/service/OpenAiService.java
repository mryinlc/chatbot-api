package com.chatbot.domain.openai.service;

import com.alibaba.fastjson.JSON;
import com.chatbot.domain.openai.IOpenAiService;
import com.chatbot.domain.openai.model.resp.OpenAiResp;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenAiService implements IOpenAiService {

    private final Logger logger = LoggerFactory.getLogger(OpenAiService.class);

    // 使用@Value注入时，必须通过ioc容器来获取对象，否则，得到的对象中的值无法注入
    @Value("${chatbot-api.apiKey}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    @Override
    public OpenAiResp getAnswer(String question) throws IOException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
            post.addHeader("Content-Type", "application/json");
            post.addHeader("Authorization", "Bearer " + apiKey);
            String data = "{\n" +
                    "    \"model\": \"text-davinci-003\",\n" +
                    "    \"prompt\": \"" + question + "\",\n" +
                    "    \"max_tokens\": 1024,\n" +
                    "    \"temperature\": 0\n" +
                    "  }";
            StringEntity stringEntity = new StringEntity(data, ContentType.create("text/json", "utf-8"));
            post.setEntity(stringEntity);
            // 给post请求设置代理
            HttpHost proxy = new HttpHost("127.0.0.1", 7890, "http");
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            post.setConfig(config);
            CloseableHttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String res = EntityUtils.toString(response.getEntity());
                logger.info("\"{}\"的答案为: {}", question, res);
                return JSON.parseObject(res, OpenAiResp.class);
            } else {
                throw new RuntimeException("获取回答失败，" + response.getStatusLine().getStatusCode());
            }
        }
    }
}
