package com.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.repository.mapper")
public class DalConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

//    @Resource
//    private DataSourceTransactionManager dataSourceTransactionManager;
//
//    @Bean
//    public TransactionTemplate transactionTemplate() {
//        return new TransactionTemplate(dataSourceTransactionManager);
//    }
//
//    @Bean
//    public TransactionTemplate transactionTemplateNew() {
//        TransactionTemplate transactionTemplate = new TransactionTemplate();
//        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//        return transactionTemplate;
//    }
}
