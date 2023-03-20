# 基础镜像
FROM openjdk:8-jre-slim
# 作者
MAINTAINER mryinlc
# 配置
ENV PARAMS=""
# 时区
ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 添加应用
ADD /chatbot-api.jar /chatbot-api.jar
ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /chatbot-api.jar $PARAMS"]

# docker build -f ./Dockerfile -t chatbot-api:1.0 .  打包镜像
# 执行镜像；docker run -e PARAMS=" --chatbot-api.groupId=你的星球ID --chatbot-api.openAiKey=自行申请 --chatbot-api.cookie=登录cookie信息" -p 8090:8090 --name chatbot-api -d chatbot-api:1.0
# --name 为运行的docker镜像指定名称
# -d 后台运行docker镜像
# -p 外部端口:内部端口