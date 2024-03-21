FROM registry.cn-chengdu.aliyuncs.com/xxx/base:jdk-agent
ENV TZ=Asia/Shanghai
ADD target/demo.jar /demo.jar
ARG TAG_APP_NAME
ENV APP_NAME=$TAG_APP_NAME
ENTRYPOINT exec java $JAVA_OPTS -jar /demo.jar
EXPOSE 10524