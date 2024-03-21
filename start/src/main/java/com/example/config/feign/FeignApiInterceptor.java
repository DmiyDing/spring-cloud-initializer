package com.example.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

/**
 * Feign拦截器。
 * 传递微服务直接参数。
 */
@Configuration
public class FeignApiInterceptor implements RequestInterceptor {

    /**
     * 用于微服务跨服务传递追踪记录ID
     */
    private static final String TRACE_ID = "TRACE_ID";



    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(TRACE_ID, MDC.get(TRACE_ID));
    }

}
