package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class OkHttpConfig {

    /**
     * 保持连接时间
     */
    private static final long KEEP_ALIVE_TIME = 5000L;

    private static final long TIMEOUT = 60000L;

    @Bean
    public OkHttpClient okHttpClient() {
        ConnectionPool connectionPool = new ConnectionPool(32, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS);

        return new OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectionPool(connectionPool)
                .retryOnConnectionFailure(false)
                .build();
    }
}
