package com.example.mq;

import com.example.mq.enums.MqTagEnum;
import com.example.utils.mq.AliRocketMqBaseConsumer;

//@Component
public class DemoConsumer extends AliRocketMqBaseConsumer {


    @Override
    public String getTag() {
        return MqTagEnum.DEMO.getCode();
    }

    @Override
    public void doConsumeMessage(String message) {

    }
}
