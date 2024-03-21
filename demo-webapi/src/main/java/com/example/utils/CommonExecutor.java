package com.example.utils;

import com.example.common.ApiResponse;
import com.example.common.exception.ApiError;
import com.example.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.function.Supplier;

@Slf4j
public class CommonExecutor {

    public static <T> ApiResponse<T> execute(Supplier<T> supplier, String bizCall, Object... param) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ApiResponse<T> result = null;

        try {
            try {
                log.info("[{}] 开始执行。入参:{}", bizCall, param);
                result = ApiResponse.success(supplier.get());
                stopWatch.stop();
                log.info("[{}] 执行成功。入参:{}；出参:{}；总耗时:{}", bizCall, param, result, (double) stopWatch.getTotalTimeMillis() / 1000.0);
            } catch (BusinessException e) {
                result = ApiResponse.error(e.getError(), e.getDetail() == null ? null : e.getDetail().toString(), e.getMessage());
                log.error("[{}] 执行业务异常。入参:{}；异常:{}", bizCall, param, e);
            } catch (Exception e) {
                result = ApiResponse.error(ApiError.UnknownError, e.getMessage());
                log.error(bizCall + "执行未知异常。异常:", e);
            }

            return result;
        } finally {
            ;
        }
    }
}
