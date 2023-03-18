package com.chatbot.test;


import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ApiTest {

    @Test
    public void getRequestTest() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/users/585224458218844/topics/footprint?count=20&group_id=28885518425541");
        String cookie = "zsxq_access_token=98160735-042C-0237-A5D9-77C5EC19706F_09E5A49BEBAB77AD; abtest_env=product; zsxqsessionid=347b23f46a530d26e95be6360f93cbd4; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross={\"distinct_id\":\"585224458218844\",\"first_id\":\"186ef88ee7d48b-08bed2d70dd6-7a545475-2073600-186ef88ee7e1424\",\"props\":{},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZWY4OGVlN2Q0OGItMDhiZWQyZDcwZGQ2LTdhNTQ1NDc1LTIwNzM2MDAtMTg2ZWY4OGVlN2UxNDI0IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTg1MjI0NDU4MjE4ODQ0In0=\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"585224458218844\"},\"$device_id\":\"186ef88ee7d48b-08bed2d70dd6-7a545475-2073600-186ef88ee7e1424\"}; UM_distinctid=186ef8e10b9a3f-034dc0e7923262-7a545475-1fa400-186ef8e10ba13b6";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41";
        get.addHeader("cookie", cookie);
        get.addHeader("user-agent", userAgent);
        get.addHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println("res = " + res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void sendCommontTest() throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/814811244214122/comments");
        String cookie = "zsxq_access_token=98160735-042C-0237-A5D9-77C5EC19706F_09E5A49BEBAB77AD; abtest_env=product; zsxqsessionid=347b23f46a530d26e95be6360f93cbd4; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross={\"distinct_id\":\"585224458218844\",\"first_id\":\"186ef88ee7d48b-08bed2d70dd6-7a545475-2073600-186ef88ee7e1424\",\"props\":{},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZWY4OGVlN2Q0OGItMDhiZWQyZDcwZGQ2LTdhNTQ1NDc1LTIwNzM2MDAtMTg2ZWY4OGVlN2UxNDI0IiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiNTg1MjI0NDU4MjE4ODQ0In0=\",\"history_login_id\":{\"name\":\"$identity_login_id\",\"value\":\"585224458218844\"},\"$device_id\":\"186ef88ee7d48b-08bed2d70dd6-7a545475-2073600-186ef88ee7e1424\"}; UM_distinctid=186ef8e10b9a3f-034dc0e7923262-7a545475-1fa400-186ef8e10ba13b6";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.41";
        post.addHeader("cookie", cookie);
        post.addHeader("user-agent", userAgent);
        post.addHeader("Content-Type", "application/json;charset=utf8");
        String json = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"包装器模式\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"mentioned_user_ids\": []\n" +
                "  }\n" +
                "}";
        StringEntity stringEntity = new StringEntity(json, ContentType.create("text/json", "utf-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println("res = " + res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
