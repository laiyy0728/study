package com.laiyy.activiti.config;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author laiyy
 * @date 2018/12/14 11:00
 * @description
 *
 * 流程实例与流程任务
 */
public class ProcessInstanceAndTask extends BaseConfig{


    @Test
    public void startProcess(){

        String processDefikey = "myProcess_1";

        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processDefikey);

        System.out.println("流程执行对象的id：" + processInstance.getId());
        System.out.println("流程实例的id：" + processInstance.getProcessInstanceId());
        System.out.println("流程定义的id：" + processInstance.getProcessDefinitionId());


    }

    /**
     * 查询正在运行的任务
     */
    @Test
    public void queryTask() throws IOException {
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> list = taskQuery.list();
        for (Task task : list) {
            System.out.println("办理人：" +task.getAssignee());
            System.out.println("id：" + task.getId());
            System.out.println("名称：" + task.getName());
            System.out.println("===================");
        }
    }
    @Test
    public void endTask(){
        // 完成这个任务，即：当前步骤完成，进行下一个步骤
        String taskId = "47502"; // 指定需要完成的任务 id
        // 完成任务，也需要任务服务
        TaskService taskService = processEngine.getTaskService();
        // 完成任务
        taskService.complete(taskId);
    }

    @Test
    public void getProcessInstanceState(){
        String processInstanceId = "25001";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();// 返回的数据要么是单行、要么是空，其他情况报错
        if (processInstance != null) {
            System.out.println(processInstanceId + " 正在运行，当前活动的任务：" + processInstance.getActivityId());
        } else {
            System.out.println(processInstanceId + "================= 空了！");
        }

    }
}
