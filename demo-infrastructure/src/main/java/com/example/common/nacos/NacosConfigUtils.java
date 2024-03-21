package com.example.common.nacos;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@RefreshScope
public class NacosConfigUtils {


    @Resource
    private NacosConfigService nacosConfigService;

    @Value("${spring.cloud.nacos.config.group}")
    private String group;

    @Value("${spring.application.name}")
    private String appName;

    /**
     * 配置文件后缀
     */
    private static final String CONFIG_SUFFIX = "-config.json";

    private static final long TIMEOUT = 5000;

    /**
     * 根据key获取配置
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        String config = getConfig();
        if (StringUtils.isBlank(config)) {
            return null;
        }

        return JSONObject.parseObject(config).getString(key);
    }

    /**
     * 获取项目动态配置
     * <p>
     * 由于yml配置大多使用的是静态配置，但是又不希望配置文件膨胀的太多，所以限制了dataId和group。
     * dataId的规则命名为appName+config后缀组成。
     *
     * @return
     */
    private String getConfig() {
        try {
            return nacosConfigService.getConfig(appName + CONFIG_SUFFIX, group, TIMEOUT);
        } catch (NacosException e) {
            log.error("[getConfig] nacos getConfig error. ", e);
            return null;
        }
    }
}
