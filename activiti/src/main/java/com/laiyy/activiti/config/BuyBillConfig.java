package com.laiyy.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author laiyy
 * @date 2018/12/7 10:23
 * @description 流程定义管理
 */
public class BuyBillConfig extends BaseConfig{

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

    @Test
    public void deployProcessDefi() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .name("采购流程")
                .addClasspathResource("BuyBill.bpmn")
                .deploy();
        System.out.println(deploy.getId() + " --> " + deploy.getName());
    }

    @Test
    public void deployProcessFromZip() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("BuyBill.zip");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .name("采购流程")
                .addZipInputStream(new ZipInputStream(inputStream))
                .deploy();
        System.out.println(deploy.getId() + " --> " + deploy.getName());
    }

    @Test
    public void queryProcessDefinition() {
        // 查看流程定义
        ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
        // 查询（类比 SQL 的 where 条件）
        // 流程定义的id，myProcess_1:2:22503，组成方式： key + 版本 + 自动生成的id
//        query = query.processDefinitionId("myProcess_1:2:22503");
        // 流程定义的 key，有 bpmn 文件的 key 决定
        query.processDefinitionKey("myProcess_1");
        // 流程定义名称
//        query.processDefinitionName("");
        // 流程定义版本
//        query.processDefinitionVersion(1);
        // 最新版本
//        query.latestVersion();
        // 版本降序排序
        List<ProcessDefinition> list = query.orderByProcessDefinitionVersion().desc()
                // 总数
//                .count()
                // 列表
                .list();

        if (!CollectionUtils.isEmpty(list)){
            list.forEach( temp -> System.out.println("流程定义id：" + temp.getId() + " ---> 流程定义key：" + temp.getKey() + " ---> 流程版本：" + temp.getVersion() + " 部署id：" + temp.getDeploymentId() + " 流程定义名称：" + temp.getName()));
        }
    }

    @Test
    public void queryResources() throws IOException {
        // 通过部署资源的 deployment id 获取资源
        String resourceName = "";
        String deploymentId = "22501";
        List<String> resourceNames = processEngine.getRepositoryService().getDeploymentResourceNames(deploymentId);
        if (!CollectionUtils.isEmpty(resourceNames)) {
            resourceName = resourceNames.get(0);
            // 读取资源，根据 deploy id 和 资源名称
            InputStream inputStream = processEngine.getRepositoryService().getResourceAsStream(deploymentId, resourceName);
            // 拷贝到本地
            File file = new File("d:/" + resourceName);
            FileUtils.copyInputStreamToFile(inputStream, file);
        }
    }

    @Test
    public void deleteProcess(){
        String deployId = "2501";
        processEngine.getRepositoryService().deleteDeployment(deployId);
    }

}
