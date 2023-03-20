package com.chatbot.application.config;

import com.chatbot.application.job.ChatbotScheduleTask;
import com.chatbot.application.utils.PropertyUtil;
import com.chatbot.domain.openai.IOpenAiService;
import com.chatbot.domain.zsxq.IZsxqApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@Configuration
public class ScheduleTaskAutoConfig implements EnvironmentAware, SchedulingConfigurer {

    private final Logger logger = LoggerFactory.getLogger(ScheduleTaskAutoConfig.class);

    private final Map<String, Map<String, Object>> taskMap = new HashMap<>();

    @Autowired
    private IOpenAiService aiService;

    @Autowired
    private IZsxqApi zsxqApi;

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "chatbot-api.";
        String tasks = environment.getProperty(prefix + "taskList");
        if (StringUtils.isEmpty(tasks)) return;
        for (String task : tasks.split(",")) {
            Map<String, Object> taskProps = PropertyUtil.handle(environment, prefix + "." + task, Map.class);
            taskMap.put(task, taskProps);
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        for (Map<String, Object> taskProps : taskMap.values()) {
            String userId = taskProps.get("userId").toString();
            String cookie = taskProps.get("cookie").toString();
            String apiKey = taskProps.get("apiKey").toString();
            // String cronExpressionBase64 = taskProps.get("cronExpression").toString();
            // String cronExpression = new String(Base64.getDecoder().decode(cronExpressionBase64), StandardCharsets.UTF_8);
            String cronExpression = taskProps.get("cronExpression").toString();
            logger.info("创建了定时任务 userId:{} cronExpression:{}", userId, cronExpression);
            taskRegistrar.addCronTask(new ChatbotScheduleTask(apiKey, userId, cookie, zsxqApi, aiService), cronExpression);
        }
    }
}
