package com.example.mq.enums;

/**
 * MQ消息TAG
 *
 * @author ding
 */

public enum MqTagEnum {
    DEMO("DEMO", "样例"),
    ;


    private String code;
    private String message;

    MqTagEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
