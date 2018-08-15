package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

/**
 * @author laiyy
 * @date 2018/8/14 16:44
 * @description
 */
public class DeleteByQuery {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();

        BulkByScrollResponse response = DeleteByQueryAction.INSTANCE.newRequestBuilder(transportClient)
                // 要删除键为 state，值为 MO 的数据
                .filter(QueryBuilders.matchQuery("state", "MO"))
                // 设置 index
                .source("bank")
                .get();

        // 总共删除了多少条
        long deleted = response.getDeleted();
        System.out.println(deleted);
    }

}
