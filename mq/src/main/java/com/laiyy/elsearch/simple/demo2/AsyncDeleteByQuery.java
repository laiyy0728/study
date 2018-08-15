package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

/**
 * @author laiyy
 * @date 2018/8/15 15:52
 * @description
 */
public class AsyncDeleteByQuery {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();
        DeleteByQueryAction.INSTANCE.newRequestBuilder(transportClient)
                .filter(QueryBuilders.matchQuery("balance", 20240))
                .source("bank")
                .execute(new ActionListener<BulkByScrollResponse>() {
                    @Override
                    public void onResponse(BulkByScrollResponse response) {
                        // 成功时的返回值
                        System.err.println(response.getDeleted());
                    }

                    @Override
                    public void onFailure(Exception e) {
                        // 失败时出现的错误
                        e.printStackTrace();
                    }
                });

    }

}
