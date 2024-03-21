package com.example.common.exception;


public enum ApiError {

    SUCCESS(200, "成功"),

    ParameterError(1001, "参数错误"),

    UnknownError(500, "未知错误"),


    ;



    /**
     * code 错误码，0-成功
     */
    private int code;

    /**
     * message 错误信息
     */
    private String message;

    ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}