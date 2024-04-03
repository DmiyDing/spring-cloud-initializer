package com.example.utils;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * mybatis-plus代码生成器
 *
 * @author lh
 */
public class CodeGenerator {
    public static void main(String[] args) {
        //表名，多个用英文逗号分割
        String tableName = "table_name";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // user.dir在根目录层
        String projectPath = System.getProperty("user.dir");
        String modulePath = "/demo-infrastructure/";
        String outputDir = projectPath + File.separator + modulePath + "src/main/java/";
        gc.setOutputDir(outputDir);
        gc.setAuthor("ding");
        gc.setOpen(false);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        //指定日期类型：Date默认是：LocalDateTime
        gc.setDateType(DateType.ONLY_DATE);
        //多次执行文件覆盖
        gc.setFileOverride(Boolean.TRUE);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/demo?autoReconnect=true&useUnicode=true&useSSL=false&characterEncoding=utf-8&allowMultiQueries=true&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("admin");
        dsc.setPassword("admin");
        mpg.setDataSource(dsc);
        // 包配置
        //模块名称
        String moduleName = "";
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        String packagePath = "com.example.repository";
        pc.setParent(packagePath);
        //路径设置
        Map<String, String> pathMap = new HashMap<>(8);
        String basePath = projectPath + File.separator + modulePath + "/src/main";
        String repositoryDir = outputDir + "com/example/repository/";
        pathMap.put(ConstVal.XML_PATH, basePath + "/resources/mapper");
        pathMap.put(ConstVal.MAPPER_PATH, repositoryDir + "mapper");
        pathMap.put(ConstVal.ENTITY_PATH, repositoryDir + "model");
        pathMap.put(ConstVal.SERVICE_PATH, repositoryDir + "service");
        pathMap.put(ConstVal.SERVICE_IMPL_PATH, repositoryDir + "service/impl");
        pc.setPathInfo(pathMap);
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        //控制 不生成 controller
        templateConfig.setController("");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
        strategy.setInclude(tableName.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
