package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author laiyy
 * @date 2018/8/16 9:15
 * @description
 */
public class BulkProcessorDemo {

    public static void main(String[] args) throws Exception {
        TransportClient transportClient = TransportClientUtils.buildClient();

        // 使用 bulk 批量处理器，提供自动 flush，基于请求的大小或数量，或者在一定时间内的请求
        BulkProcessor processor = BulkProcessor.builder(
                // 添加 elasticsearch 客户端
                transportClient,
                // 批量处理流程监听器
                new BulkProcessor.Listener() {
                    /**
                     * 在批量处理之前调用
                     * @param executionId 流程 id
                     * @param bulkRequest 请求体
                     */
                    @Override
                    public void beforeBulk(long executionId, BulkRequest bulkRequest) {
                        System.err.println("------------开始批量处理，流程id：" + executionId + "，请求体：" + bulkRequest.getDescription());
                    }

                    /**
                     * 在批量处理之后调用
                     * @param executionId 流程 id
                     * @param bulkRequest 请求体
                     * @param bulkResponse 响应体
                     */
                    @Override
                    public void afterBulk(long executionId, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                        System.err.println("------------批量处理完成，流程id：" + executionId + "，请求体：" + bulkRequest.getDescription() + "，请求结果：" + bulkResponse);
                    }

                    /**
                     * 在批量调用失败并抛出异常后调用
                     * @param executionId 流程 id
                     * @param bulkRequest 请求体
                     * @param throwable 可能跑出的异常
                     */
                    @Override
                    public void afterBulk(long executionId, BulkRequest bulkRequest, Throwable throwable) {
                        System.err.println("------------批量处理失败，流程id：" + executionId + "，请求体：" + bulkRequest.getDescription() + "，出现的错误：" + throwable.getLocalizedMessage());
                    }
                })
                // 每 10000 条请求执行一次（默认为 1000）
                .setBulkActions(10000)
                // 每 5MB 数据执行一次（默认为 5MB）
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                // 无论请求数量多少，每 5 秒刷新一次（默认不设置）
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                // 设置并发请求树。值为 0 表示只允许执行单个请求。值为 1 表示运行执行一个并发请求，
                // 同时积累新的批量请求（设置为 1，意味着刷新操作将异步操作）
                .setConcurrentRequests(1)
                // 设置为退避策略，重试次数为 8 次，启动延迟为 50 ms，总等待时间约为 5.1 秒
                .setBackoffPolicy(
                        // 设置自定义退避策略，该策略最初将等待 100 毫秒，呈指数级增长。最多重试 3 次。
                        // 每当一个或多个批量请求失败时，尝试重试，EsRejectedExecutionException 表示可用于
                        // 处理请求的计算资源太少。要禁用退避策略，需要使用 noBackoff()
                        BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build();

        // 添加批量请求
        processor.add(new IndexRequest("twitter", "tweet", "1")
                .source(XContentFactory.jsonBuilder().startObject()
                        .field("user", "laiyy")
                        .field("postDate", new Date())
                        .field("message", "request")
                        .endObject()));
        processor.add(new DeleteRequest("twitter", "tweet", "2"));

        // 开始执行批量处理
        processor.flush();

        // 关闭 processor（延迟 5 秒）
        processor.awaitClose(5, TimeUnit.SECONDS);

        // 刷新索引，并获取索引内容
        RefreshResponse refreshResponse = transportClient.admin().indices().prepareRefresh("twitter").get();
        System.err.println(refreshResponse);

        // 获取索引内容
        System.out.println(transportClient.prepareSearch("twitter").get());

    }

}
