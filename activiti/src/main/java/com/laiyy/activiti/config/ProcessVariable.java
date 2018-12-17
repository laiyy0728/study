//package com.laiyy.activiti.config;
//
//import org.activiti.editor.language.json.converter.util.CollectionUtils;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.repository.Deployment;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.activiti.engine.task.TaskQuery;
//import org.junit.Test;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @author laiyy
// * @date 2018/12/14 16:23
// * @description 流程变量
// */
//public class ProcessVariable extends BaseConfig {
//
//    @Test
//    public void deploy() {
//        // 获取仓库服务：管理定义流程
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        // 创建部署
//        Deployment deploy = repositoryService.createDeployment() // 返回部署的构建器
//                .addClasspathResource("payBill.bpmn") // 从类路径下添加资源
//                .name("PayBill：支付流程") // 设置部署的名字
//                .category("支付类别") // 设置类别
//                .deploy();
//        System.out.println("部署成功后返回的 id：" + deploy.getId() + "，部署的名称：" + deploy.getName());
//    }
//
//    @Test
//    public void startProcess() {
//        // 执行流程 -- 执行流程属于运行，需要获取运行时服务 RuntimeService
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        // startProcessInstanceById 使用 act_re_procdef 中的 ID_ 字段，该字段自动生成，不便于理解
//        // startProcessInstanceByKey 使用 act_re_procdef 中的 KEY_ 字段，该字段手动执行，便于理解，但是需要格外注意不能重复
//        // 如果一个流程进行了多次修改，那么 KEY_ 和 NAME_ 必须一样，且运行时执行最后一个 VERSION_ 版本
//        // 取得流程实例
//        ProcessInstance instance = runtimeService.startProcessInstanceByKey("appayBill");
//        System.out.println("流程实例id：" + instance.getId() + " ---> 流程定义id： " + instance.getProcessDefinitionId());
//    }
//
//    @Test
//    public void queryTask() {
//        // 任务办理人（第一步是zhangsan办理）
//        String user = "zhangsan";
//        // 获取任务服务
//        TaskService taskService = processEngine.getTaskService();
//        // 创建任务查询对象
//        TaskQuery taskQuery = taskService.createTaskQuery();
//        // 指定办理人，获取办理人的任务列表
//        List<Task> list = taskQuery.taskAssignee(user).list();
//        // 连理任务列表
//        if (!CollectionUtils.isEmpty(list)) {
//            for (Task task : list) {
//                System.out.println("任务办理人：" + task.getAssignee() + " --> 任务id：" + task.getId() + " --> 任务名称：" + task.getName());
//            }
//        }
//    }
//
//    @Test
//    public void endTask() {
//        // 完成这个任务，即：当前步骤完成，进行下一个步骤
//        String taskId = "57502"; // 指定需要完成的任务 id
//        // 完成任务，也需要任务服务
//        TaskService taskService = processEngine.getTaskService();
//        // 完成任务
//        taskService.complete(taskId);
//    }
//
//    @Test
//    public void setVariable(){
//        // 使用 taskService 设置流程变量
//        TaskService taskService = processEngine.getTaskService();
//        String taskId = "57502", variableName = "cost";
//        taskService.setVariable(taskId, variableName, 1000);
//        taskService.setVariableLocal(taskId, "申请人", "团长");
//        taskService.setVariable(taskId, "申请时间", new Date());
//
//        System.out.println("设置成功！");
//    }
//
//    @Test
//    public void getVariable(){
//        // 使用 taskService 查询流程变量
//        TaskService taskService = processEngine.getTaskService();
//        String taskId = "57502";
//        Integer cost = (Integer) taskService.getVariable(taskId, "cost");
//        Date date = (Date) taskService.getVariableLocal(taskId, "申请时间");
//        String user = (String) taskService.getVariable(taskId, "申请人");
//        System.out.println("申请人：" + user + " 申请时间： " + date+ " cost：" + cost);
//    }
//
//
//
//    /**
//     * 模拟变量的设置
//     */
//    @Test
//    public void getAndSetProcessVariable() {
//        // 两种方式  taskService、runtimeService
//        TaskService taskService = processEngine.getTaskService();
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//
//        // 1、通过 runtimeService 设置
//        // executionId 执行对象
//        // variableName 变量名
//        // value 变量值
//        String executionId, variableName;
//        Object value;
//        // 设置单个变量
////        runtimeService.setVariable(executionId, variableName);
//        // 设置本执行对象的变量，该对象的作用域只在当前的执行对象
////        runtimeService.setVariableLocal();
//        // 设置多个变量，存放在 Map<String, Object>
////        runtimeService.setVariables();
//
//        // 2、使用 taskService 设置，方法与 runtimeService 一致
//        // taskId 任务id，范围比 runtime 小
//        // variableName 变量名
//        // value 变量值
//
//        // 3、当流程开始执行的时候，可以设置变量
////        startProcessInstanceByKey 可以传入 processKey 和多个变量， values：Map<String, Object>
////        processEngine.getRuntimeService()
////                .startProcessInstanceByKey(processKey, values)
//
//        // 4、当执行任务时设置变量 values：Map<String, Object>
////        processEngine.getTaskService().complete(taskId, values);
//
//
//        /* 取值 */
////        runtimeService.getVariable(executionId, key) 去单个变量
////        runtimeService.getVariableLocal(executionId, key) 取本执行对象的单个变量
////        runtimeService.getVariables(executionId) // 取多个变量
//
////        taskService.getVariable(taskId, key)
////        taskService.getVariableLocal(taskId, key)
////        taskService.getVariables(taskId)
//    }
//
//}
