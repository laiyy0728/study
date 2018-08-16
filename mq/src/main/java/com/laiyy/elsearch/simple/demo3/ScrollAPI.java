package com.laiyy.elsearch.simple.demo3;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @author laiyy
 * @date 2018/8/16 15:07
 * @description
 */
public class ScrollAPI {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();

        SearchResponse searchResponse = transportClient.prepareSearch("customer")
                .addSort(FieldSortBuilder.DOC_FIELD_NAME, SortOrder.DESC)
                // 滚动时间 60 秒
                .setScroll(new TimeValue(60000))
                // 100 条
                .setSize(100).get();

        do {
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                // 打印获取到的每条信息的 source
                System.err.println(searchHit.getSourceAsString());
            }

            searchResponse = transportClient.prepareSearchScroll(searchResponse.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
        } while (searchResponse.getHits().getHits().length != 0);



    }

}
