package com.chatbot.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.chatbot.domain.zsxq.IZsxqApi;
import com.chatbot.domain.zsxq.model.req.CommentReq;
import com.chatbot.domain.zsxq.model.req.ReqData;
import com.chatbot.domain.zsxq.model.resp.CommentResp;
import com.chatbot.domain.zsxq.model.resp.SubjectsRsp;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ZsxqApi implements IZsxqApi {
    private final Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public SubjectsRsp getSubjectsRepByUserId(String userId, String cookie) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/users/" + userId + "/topics/footprint?count=20&group_id=28885518425541");
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41";
        get.addHeader("cookie", cookie);
        get.addHeader("user-agent", userAgent);
        get.addHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            // System.out.println("res = " + res);
            logger.info("获取的主题信息为: {}", res);
            // 设置转换为对象时，将驼峰映射为下划线
            ParserConfig.getGlobalInstance().propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
            return JSON.parseObject(res, SubjectsRsp.class);
        } else {
            throw new RuntimeException("获取主题信息失败，" + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public CommentResp addCommentByTopicId(String topicId, String cookie, String comment) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/comments");
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41";
        post.addHeader("cookie", cookie);
        post.addHeader("user-agent", userAgent);
        post.addHeader("Content-Type", "application/json;charset=utf8");
        /* 添加评论的JSON信息格式
        String json = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"包装器模式\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";*/
        CommentReq commentReq = new CommentReq(new ReqData(comment));
        // 配置转JSON时，将驼峰映射为下划线
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        logger.info("添加评论请求体为: {}", JSON.toJSONString(commentReq, config));
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(commentReq, config), ContentType.create("text/json", "utf-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            // System.out.println("res = " + res);
            logger.info("添加评论请求的响应为: {}", res);
            return JSON.parseObject(res, CommentResp.class);
        } else {
            // System.out.println(response.getStatusLine().getStatusCode());
            throw new RuntimeException("添加评论失败，" + response.getStatusLine().getStatusCode());
        }
    }
}
