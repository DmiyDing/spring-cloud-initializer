package com.example.utils.mq;

import com.aliyun.openservices.ons.api.*;
import com.example.common.exception.BusinessException;
import com.example.utils.IdempotentUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Properties;

/**
 * @author ding
 */
@Slf4j
@Component
public abstract class AliRocketMqBaseConsumer implements MessageListener {
    /**
     * 前缀
     */
    private static final String GID = "GID_";
    private static final String MEG_ID = "MEG_ID";
    private static final String MEG_KEY_PRE = "MQ_";

    @Resource
    private AliyunRocketMqConfig mqConfig;

    @Resource
    private IdempotentUtil idempotentUtil;

    protected Consumer consumer;

    @PostConstruct
    public void init() {
        //配置文件
        Properties properties = mqConfig.getProperties();
        //消费者组规则：GID_tag_topic
        properties.setProperty(PropertyKeyConst.GROUP_ID, GID + getTag() + "_" + mqConfig.getTopic());
        consumer = ONSFactory.createConsumer(properties);
        consumer.subscribe(mqConfig.getTopic(), getTag(), this);
        consumer.start();
    }

    @PreDestroy
    public void destroy() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }


    @Override
    public Action consume(final Message message, final ConsumeContext context) {
        if (message == null) {
            return Action.CommitMessage;
        }


        try {
            log.info("[consume msg_ID]:{}", message.getMsgID());
            MDC.put(MEG_KEY_PRE, message.getMsgID());
            Integer timeout = 5 * 60 * 1000;

            Boolean idempotent = idempotentUtil.idempotent(MEG_KEY_PRE + message.getMsgID(), timeout);
            if (!idempotent) {
                return Action.CommitMessage;
            }
            MDC.put(MEG_ID, message.getMsgID());
            if (message.getBody() == null) {
                return Action.CommitMessage;
            }

            log.info("[consume msg_ID]:{},{}", message.getMsgID(), new String(message.getBody()));

            String messageBody = new String(message.getBody());
            if (StringUtils.isEmpty(messageBody)) {
                return Action.CommitMessage;
            }
            doConsumeMessage(messageBody);
        } catch (BusinessException e) {
            log.error("[consume] 执行业务异常。入参:{}；异常:{}", message, e);
            idempotentUtil.removeIdempotent(MEG_KEY_PRE + message.getMsgID());
            return Action.ReconsumeLater;
        } catch (Exception e) {
            log.error("[consume] 未知异常。", e);
            idempotentUtil.removeIdempotent(MEG_KEY_PRE + message.getMsgID());
            return Action.ReconsumeLater;
        } finally {
            MDC.remove(MEG_ID);
            MDC.remove(MEG_KEY_PRE);
        }
        return Action.CommitMessage;
    }

    /**
     * 消息Tag
     *
     * @return
     */
    public abstract String getTag();


    /**
     * 消息处理
     *
     * @param message
     * @return
     */
    public abstract void doConsumeMessage(String message);


}
