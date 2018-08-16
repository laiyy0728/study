package com.laiyy.elsearch.simple.demo3;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author laiyy
 * @date 2018/8/16 15:07
 * @description
 */
public class SearchAPI {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();
        SearchResponse searchResponse = transportClient.prepareSearch("bank")
                .setTypes("account")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                // 查询 age 在 37 到 40 之间的数据
                .setPostFilter(QueryBuilders.rangeQuery("age").from(37).to(40))
                // 查询 10 条
                .setFrom(0).setSize(10).setExplain(true)
                .get();

        System.err.println(searchResponse.toString());
    }

}
