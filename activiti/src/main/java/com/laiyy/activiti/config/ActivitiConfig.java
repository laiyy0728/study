//package com.laiyy.activiti.config;
//
//import org.activiti.engine.ProcessEngine;
//import org.activiti.engine.ProcessEngineConfiguration;
//import org.activiti.engine.ProcessEngines;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.repository.Deployment;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.activiti.engine.task.TaskQuery;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.util.CollectionUtils;
//
//import java.util.List;
//
///**
// * @author laiyy
// * @date 2018/12/5 17:06
// * @description
// */
//public class ActivitiConfig {
//
//    private ProcessEngine processEngine;
//
//    @Before
//    public void buildProcessEngine() {
//        // 1、取得 ProcessEngineConfiguration 对象
//        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
//        // 2、设置数据库属性
//        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
//        configuration.setJdbcUrl("jdbc:mysql:///activiti?createDatabaseIfNotExist=true&useUnicode=true&charsetEncoding=utf8&serverTimezone=Hongkong");
//        configuration.setJdbcUsername("root");
//        configuration.setJdbcPassword("123456");
//
//        // 3、设置创建表的策略，没有表时自动创建
//        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//
//        // 创建流程引擎
//
//        processEngine = configuration.buildProcessEngine();
//        System.out.println("流程创建成功");
//
//
//        processEngine = ProcessEngines.getDefaultProcessEngine();
//
//    }
//
//    @Test
//    public void deploy() {
//        // 获取仓库服务：管理定义流程
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        // 创建部署
//        Deployment deploy = repositoryService.createDeployment() // 返回部署的构建器
//                .addClasspathResource("LevelBill.bpmn") // 从类路径下添加资源
//                .name("LevelBill：请假单流程") // 设置部署的名字
//                .category("办公类别") // 设置类别
//                .deploy();
//        System.out.println("部署成功后返回的 id：" + deploy.getId() + "，部署的名称：" + deploy.getName());
//    }
//
//
//    @Test
//    public void startProcess(){
//        // 执行流程 -- 执行流程属于运行，需要获取运行时服务 RuntimeService
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        // startProcessInstanceById 使用 act_re_procdef 中的 ID_ 字段，该字段自动生成，不便于理解
//        // startProcessInstanceByKey 使用 act_re_procdef 中的 KEY_ 字段，该字段手动执行，便于理解，但是需要格外注意不能重复
//        // 如果一个流程进行了多次修改，那么 KEY_ 和 NAME_ 必须一样，且运行时执行最后一个 VERSION_ 版本
//        // 取得流程实例
//        ProcessInstance instance = runtimeService.startProcessInstanceByKey("levelBill");
//        System.out.println("流程实例id：" + instance.getId() + " ---> 流程定义id： " + instance.getProcessDefinitionId());
//    }
//
//    @Test
//    public void queryTask(){
//        // 任务办理人（第一步是zhangsan办理）
//        String user = "zhangsan";
//        // 获取任务服务
//        TaskService taskService = processEngine.getTaskService();
//        // 创建任务查询对象
//        TaskQuery taskQuery = taskService.createTaskQuery();
//        // 指定办理人，获取办理人的任务列表
//        List<Task> list = taskQuery.taskAssignee(user).list();
//        // 连理任务列表
//        if (!CollectionUtils.isEmpty(list)){
//            for (Task task : list) {
//                System.out.println("任务办理人：" + task.getAssignee() + " --> 任务id：" + task.getId() + " --> 任务名称：" + task.getName());
//            }
//        }
//    }
//
//    @Test
//    public void endTask(){
//        // 完成这个任务，即：当前步骤完成，进行下一个步骤
//        String taskId = "12502"; // 指定需要完成的任务 id
//        // 完成任务，也需要任务服务
//        TaskService taskService = processEngine.getTaskService();
//        // 完成任务
//        taskService.complete(taskId);
//    }
//
//}
