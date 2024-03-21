package com.example.utils.mq;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 阿里云RocketMQ
 *
 * @author lh
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.rocketmq")
public class AliyunRocketMqConfig {
    private String accessKey;
    private String secretKey;
    private String namesrvAddr;
    private String sendMsgTimeoutMillis;
    private String topic;


    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.AccessKey, this.accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, this.secretKey);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, this.namesrvAddr);
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, this.sendMsgTimeoutMillis);
        return properties;
    }
}
