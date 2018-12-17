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
 * @date 2018/12/17 10:54
 * @description
 */
public class Assignee extends BaseConfig{


    @Test
    public void init() {
        deploy("payBill.bpmn", "支付流程", "支付分类");

        Map<String, Object> params = new HashMap<>();
        params.put("userId", "团长");
        startProcess("appayBill", params);




    }

    @Test
    public void queryTask() {
        // 任务办理人（第一步是zhangsan办理）
        String user = "团长";
        // 获取任务服务
        TaskService taskService = processEngine.getTaskService();
        // 创建任务查询对象
        TaskQuery taskQuery = taskService.createTaskQuery();
        // 指定办理人，获取办理人的任务列表
        List<Task> list = taskQuery.taskAssignee(user).list();
        // 连理任务列表
        if (!CollectionUtils.isEmpty(list)) {
            for (Task task : list) {
                System.out.println("任务办理人：" + task.getAssignee() + " --> 任务id：" + task.getId() + " --> 任务名称：" + task.getName());
            }
        }
    }

}
