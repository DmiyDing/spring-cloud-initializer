package com.example.utils.mq;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 阿里云rocketmq消息发送根据
 *
 * @author lh
 */
@Component
public class AliRocketMqProducer {

    @Autowired
    private AliyunRocketMqConfig aliRocketMqConfig;

    public static Producer producer;


    /**
     * 初始化生产者
     *
     * @return
     */
    public Producer getProducer() {
        if (producer == null || producer.isClosed()) {
            producer = ONSFactory.createProducer(aliRocketMqConfig.getProperties());
            producer.start();
        }
        return producer;
    }

    /**
     * 发送消息
     *
     * @param messageBody
     * @param messageTag
     * @param messageKey
     * @return TRUE：发送成功。FALSE：发送失败
     */
    public Boolean send(String messageBody, String messageTag, String messageKey) {
        return send(messageBody, aliRocketMqConfig.getTopic(), messageTag, messageKey);
    }

    /**
     * 发送消息
     *
     * @param messageBody
     * @param topic
     * @param messageTag
     * @param messageKey
     * @return TRUE：发送成功。FALSE：发送失败
     */
    public Boolean send(String messageBody, String topic, String messageTag, String messageKey) {
        Message message = new Message(
                topic,
                messageTag,
                messageKey,
                messageBody.getBytes());
        SendResult send = getProducer().send(message);
        if (send != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


}
