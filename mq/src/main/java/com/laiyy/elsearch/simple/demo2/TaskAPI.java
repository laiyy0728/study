package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.admin.cluster.node.tasks.get.GetTaskResponse;
import org.elasticsearch.action.admin.cluster.node.tasks.list.ListTasksResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.reindex.BulkByScrollTask;
import org.elasticsearch.index.reindex.RethrottleAction;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.tasks.TaskId;
import org.elasticsearch.tasks.TaskInfo;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/16 14:33
 * @description
 */
public class TaskAPI {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();

        ListTasksResponse taskList = transportClient.admin().cluster().prepareListTasks()
                .setActions(UpdateByQueryAction.NAME).setDetailed(true).get();

        for (TaskInfo info : taskList.getTasks()) {
            TaskId taskId = info.getTaskId();
            BulkByScrollTask.Status status = (BulkByScrollTask.Status) info.getStatus();
            System.err.println(taskId.toString());
            System.err.println(status.toString());
            System.out.println("==================================");
        }

        GetTaskResponse response = transportClient.admin().cluster().prepareGetTask(TaskId.EMPTY_TASK_ID).get();
        System.err.println(response);

        List<TaskInfo> tasks = transportClient.admin().cluster().prepareCancelTasks().setActions(UpdateByQueryAction.NAME).get().getTasks();
        for (TaskInfo task : tasks) {
            TaskId taskId = task.getTaskId();
            BulkByScrollTask.Status status = (BulkByScrollTask.Status) task.getStatus();
            System.err.println(taskId.toString());
            System.err.println(status.toString());
            System.out.println("==================================");
        }

        tasks = transportClient.admin().cluster().prepareCancelTasks().setTaskId(TaskId.EMPTY_TASK_ID).get().getTasks();
        for (TaskInfo task : tasks) {
            TaskId taskId = task.getTaskId();
            BulkByScrollTask.Status status = (BulkByScrollTask.Status) task.getStatus();
            System.err.println(taskId.toString());
            System.err.println(status.toString());
            System.out.println("==================================");
        }

        taskList = RethrottleAction.INSTANCE.newRequestBuilder(transportClient)
                .setTaskId(TaskId.EMPTY_TASK_ID)
                .setRequestsPerSecond(2.0f)
                .get();
        for (TaskInfo task : taskList.getTasks()) {
            TaskId taskId = task.getTaskId();
            BulkByScrollTask.Status status = (BulkByScrollTask.Status) task.getStatus();
            System.err.println(taskId.toString());
            System.err.println(status.toString());
            System.out.println("==================================");
        }


    }

}
