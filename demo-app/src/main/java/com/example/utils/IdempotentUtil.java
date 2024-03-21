package com.example.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 幂等根据
 * 通过REDIS实现
 *
 * @author ding
 */
@Component
public class IdempotentUtil {
    public static final Integer IDEMPOTENT_KEY_EXPIRE_MS = 24 * 60 * 60 * 1000;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 存REDIS
     *
     * @param key
     * @return TRUE：成功，FALSE：失败
     */
    public Boolean idempotent(String key) {
        return stringRedisTemplate
                .opsForValue()
                .setIfAbsent(key, String.valueOf(System.currentTimeMillis()), IDEMPOTENT_KEY_EXPIRE_MS, TimeUnit.MILLISECONDS);
    }

    public Boolean idempotent(String key, Integer timeout) {

        if (timeout == null) {
            timeout = IDEMPOTENT_KEY_EXPIRE_MS;
        }

        return stringRedisTemplate
                .opsForValue()
                .setIfAbsent(key, String.valueOf(System.currentTimeMillis()), timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 移除KEY
     *
     * @param key
     */
    public void removeIdempotent(String key) {
        stringRedisTemplate.delete(key);
    }


}
