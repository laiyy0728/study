package com.laiyy.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.Map;

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


    protected void startProcess(String processDefikey, Map<String, Object> params) {
        ProcessInstance processInstance;
        if (CollectionUtils.isEmpty(params)) {
            processInstance = processEngine.getRuntimeService()
                    .startProcessInstanceByKey(processDefikey);
        } else {
            processInstance = processEngine.getRuntimeService()
                    .startProcessInstanceByKey(processDefikey, params);
        }

        System.out.println("流程执行对象的id：" + processInstance.getId());
        System.out.println("流程实例的id：" + processInstance.getProcessInstanceId());
        System.out.println("流程定义的id：" + processInstance.getProcessDefinitionId());
    }

    protected void deploy(String file, String name, String category) {
        // 获取仓库服务：管理定义流程
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 创建部署
        // 返回部署的构建器
        Deployment deploy = repositoryService.createDeployment()
                // 从类路径下添加资源
                .addClasspathResource(file)
                // 设置部署的名字
                .name(name)
                // 设置类别
                .category(category)
                .deploy();
        System.out.println("部署成功后返回的 id：" + deploy.getId() + "，部署的名称：" + deploy.getName());
    }

    protected void completeTask(String taskId) {
        // 完成任务，也需要任务服务
        TaskService taskService = processEngine.getTaskService();
        // 完成任务
        taskService.complete(taskId);
    }
}
