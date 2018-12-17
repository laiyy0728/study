package com.laiyy.activiti.config;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/12/17 9:24
 * @description
 */
public class SequenceFlow extends BaseConfig{

    @Test
    public void init(){
        // 部署
//        String file = "sequenceFlow.bpmn", name="分支流程", category="分支类别";
//        deploy(file, name, category);

        // 启动
//        startProcess("sequence");

        // 执行任务
//        completeTask("80003");
    }

    @Test
    public void sequenceFlowStart(){
        // 这个任务有分支
        String taskId = "85005";

        Map<String, Object> params = new HashMap<>();
        params.put("message", "不知道重要");
        processEngine.getTaskService().complete(taskId, params);
        System.out.println("无法对应分支，执行完毕");
    }

    @Test
    public void querySequenceTask(){
        TaskService taskService = processEngine.getTaskService();
        // 创建任务查询对象
        TaskQuery taskQuery = taskService.createTaskQuery();
        // 指定办理人，获取办理人的任务列表
        List<Task> list = taskQuery.list();
        // 连理任务列表
        if (!CollectionUtils.isEmpty(list)) {
            for (Task task : list) {
                System.out.println("任务办理人：" + task.getAssignee() + " --> 任务id：" + task.getId() + " --> 任务名称：" + task.getName());
            }
        }
    }
}
