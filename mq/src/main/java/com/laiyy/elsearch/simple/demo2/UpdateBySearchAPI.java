package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.sort.SortOrder;

import java.util.Collections;

/**
 * @author laiyy
 * @date 2018/8/16 10:00
 * @description
 */
public class UpdateBySearchAPI {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();

        // 调用updateByQuery API首先获取索引的快照，索引使用内部版本控制找到的任何文档。

        // updateByQuery的最简单用法是更新索引中的每个文档而不更改源。 此用法可用于获取新属性或其他在线映射更改。
        UpdateByQueryRequestBuilder updateByQuery = UpdateByQueryAction.INSTANCE.newRequestBuilder(transportClient);
        // 设置索引，设置终止版本冲突为 false
        updateByQuery.source("bank").abortOnVersionConflict(false);
        BulkByScrollResponse response = updateByQuery.get();
        System.err.println(response.toString());

        BulkByScrollResponse scrollResponse = UpdateByQueryAction.INSTANCE.newRequestBuilder(transportClient)
                // 设置需要更新的 index
                .source("bank")
                // 设置过滤规则
                .filter(QueryBuilders.matchQuery("state", "MD"))
                // 设置限制索引更新数量
                .size(10)
                // 设置更新脚本
                .script(new Script("ctx._source.state='MD1'"))
                .get();
        System.err.println(scrollResponse.toString());

        SearchResponse searchResponse = UpdateByQueryAction.INSTANCE.newRequestBuilder(transportClient)
                // 返回的是 UpdateByQueryRequestBuilder
                .source("bank")
                // 返回的是 SearchRequestBuilder
                .source()
                // 查询多少条数据
                .setSize(5)
                // 设置排序字段、排序方式
                .addSort("balance", SortOrder.DESC)
                .get();

        System.err.println(searchResponse.toString());

        updateByQuery = UpdateByQueryAction.INSTANCE.newRequestBuilder(transportClient);
        updateByQuery.source("bank")
                .script(new Script(
                        ScriptType.INLINE,
                        // 设置脚本语言
                        "painless",
                        // 设置脚本
                        "if (ctx._source.state == 'PA') {" +
                                "    ctx._source.firstname='laiyy'" +
                                "}",
                        Collections.emptyMap()
                ));
        System.out.println(updateByQuery.get().toString());

        SearchResponse searchResponse1 = UpdateByQueryAction.INSTANCE.newRequestBuilder(transportClient)
                // 设置多个索引
                .source("twitter", "customer", "index")
                .source()
                // 设置多个文档类型
                .setTypes("account", "type", "_doc").get();

        System.err.println(searchResponse1);

        searchResponse = UpdateByQueryAction.INSTANCE.newRequestBuilder(transportClient)
                .source().setRouting("cat").get();

        System.err.println(searchResponse);

        scrollResponse = UpdateByQueryAction.INSTANCE.newRequestBuilder(transportClient)
                .setPipeline("hurray").get();

        System.err.println(scrollResponse);

    }

}
