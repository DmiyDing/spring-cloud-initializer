package com.example.common;

import com.example.common.exception.ApiError;
import com.example.common.exception.BusinessException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * API 响应
 *
 * @author ding
 */
@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 数据对象
     */
    private T data;

    /**
     * 异常堆栈信息
     */
    private String stackTraceMessage;

    /**
     * 返回成功对象
     *
     * @param data - 数据对象
     * @return ApiResponse - 返回对象
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ApiError.SUCCESS, data);
    }

    /**
     * 返回失败对象
     *
     * @param data - 数据对象
     * @return ApiResponse - 返回对象
     */
    public static <T> ApiResponse<T> error(ApiError error, T data) {
        return new ApiResponse<>(error, data);
    }

    public static <T> ApiResponse<T> error(ApiError error, String message, String stackTraceMessage) {
        return new ApiResponse<>(error, message, stackTraceMessage);
    }

    public static <T> ApiResponse<T> error(ApiError error, String message) {
        return new ApiResponse<>(error, message);
    }

    /**
     * 返回失败对象
     *
     * @param data - 数据对象
     * @return ApiResponse - 返回对象
     */
    public static <T> ApiResponse<T> error(BusinessException ex, T data) {
        return new ApiResponse<>(ex.getError(), data);
    }

    /**
     * 返回失败对象
     *
     * @param data - 数据对象
     * @return ApiResponse - 返回对象
     */
    public static <T> ApiResponse<T> error(BusinessException ex, String message, T data) {
        return new ApiResponse<>(ex.getError(), message, data);
    }

    /**
     * 返回失败对象
     *
     * @param data - 数据对象
     * @return ApiResponse - 返回对象
     */
    public static <T> ApiResponse<T> error(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }


    private ApiResponse(ApiError error, T data) {
        this.code = error.getCode();
        this.message = error.getMessage();
        this.data = data;
    }

    private ApiResponse(ApiError error, String message) {
        this.code = error.getCode();
        this.message = error.getMessage();
        this.stackTraceMessage = message;
    }


    private ApiResponse(ApiError error, String message, T data) {
        this.code = error.getCode();
        this.message = message;
        this.data = data;
    }

    private ApiResponse(ApiError error, String message, String stackTraceMessage) {
        this.code = error.getCode();
        this.message = message;
        this.stackTraceMessage = stackTraceMessage;
    }

    protected ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
