package com.example.common.exception;

public class BusinessException extends RuntimeException {
    private ApiError error;
    private Object detail;
    private String mes;
    private int code;

    public BusinessException(ApiError error) {
        super(String.format("%s-%s", error.getCode(), error.getMessage()));
        this.error = error;
    }

    public BusinessException(ApiError error, Object detail) {
        super(String.format("%s-%s:%s", error.getCode(), error.getMessage(), null == detail ? null : detail.toString()));
        this.error = error;
        this.detail = detail;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.mes = message;
    }

    public BusinessException(String message) {
        super(message);
    }

    public ApiError getError() {
        return this.error;
    }

    public Object getDetail() {
        return this.detail;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}