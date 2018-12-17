package com.laiyy.activiti.config;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/12/17 10:20
 * @description
 */
public class ExclusiveGateway extends BaseConfig{

    @Test
    public void init(){
//        deploy("exclusiveGateway.bpmn", "排他网关", "排他网关");

//        startProcess("bankBill");

    }

    @Test
    public void start(){
        String taskId = "117505";
        Map<String, Object> params = new HashMap<>();
        params.put("visitor", 2);
        processEngine.getTaskService().complete(taskId, params);
        System.out.println("visitor == 3，执行完毕");
    }

    @Test
    public void end(){
        completeTask("112504");
    }

}
