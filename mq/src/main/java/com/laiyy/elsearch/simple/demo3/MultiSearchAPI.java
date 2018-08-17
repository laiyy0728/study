package com.laiyy.elsearch.simple.demo3;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author laiyy
 * @date 2018/8/16 15:35
 * @description
 */
public class MultiSearchAPI {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();

        SearchRequestBuilder elasticsearch = transportClient.prepareSearch().setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(10);
        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch().setQuery(QueryBuilders.matchQuery("state", "PA")).setSize(10);

        MultiSearchResponse items = transportClient.prepareMultiSearch().add(elasticsearch).add(searchRequestBuilder).get();
        for (MultiSearchResponse.Item item : items.getResponses()) {
            SearchResponse response = item.getResponse();
            System.err.println(response);
        }

    }

}
