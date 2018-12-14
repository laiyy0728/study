package com.laiyy.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Before;

/**
 * @author laiyy
 * @date 2018/12/14 11:00
 * @description
 */
public class BaseConfig {
    protected ProcessEngine processEngine;

    @Before
    public void buildProcessEngine() {
        // 1、取得 ProcessEngineConfiguration 对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        // 2、设置数据库属性
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql:///activiti?createDatabaseIfNotExist=true&useUnicode=true&charsetEncoding=utf8&serverTimezone=Hongkong");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("123456");

        // 3、设置创建表的策略，没有表时自动创建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 创建流程引擎

        processEngine = configuration.buildProcessEngine();
        System.out.println("流程创建成功");


        processEngine = ProcessEngines.getDefaultProcessEngine();

    }
}
