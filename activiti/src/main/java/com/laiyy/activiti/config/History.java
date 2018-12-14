package com.laiyy.activiti.config;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/12/14 11:44
 * @description
 */
public class History extends BaseConfig {

    /**
     * 历史实例
     */
    @Test
    public void queryHistoryProcInst() {
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().list();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(item -> {
                System.out.println("流程实例id" + item.getId());
                System.out.println("流程实例定义id" + item.getProcessDefinitionId());
                System.out.println("流程开始时间" + item.getStartTime());
                System.out.println("流程结束时间" + item.getEndTime());
                System.out.println("=================");
            });
        }
    }

    /**
     * 历史任务
     */
    @Test
    public void queryHistoryTask(){
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().list();
        if (!CollectionUtils.isEmpty(list)){
            list.forEach(item -> {
                System.out.println("历史流程实例id：" + item.getId());
                System.out.println("历史流程定义id：" + item.getTaskDefinitionKey());
                System.out.println("任务名称：" + item.getName());
                System.out.println("处理人：" + item.getAssignee());
                System.out.println("================");
            });
        }
    }

}
