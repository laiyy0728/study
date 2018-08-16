package com.laiyy.elsearch.simple.demo3;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

/**
 * @author laiyy
 * @date 2018/8/16 15:40
 * @description
 */
public class Aggregations {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();

        SearchResponse searchResponse = transportClient.prepareSearch()
                .setQuery(QueryBuilders.matchAllQuery())
                .addAggregation(
                        AggregationBuilders.terms("agg1").field("state")
                )
                .addAggregation(
                        AggregationBuilders.dateHistogram("agg2")
                                .field("age")
                                .dateHistogramInterval(DateHistogramInterval.YEAR)
                )
                .get();

        Terms terms = searchResponse.getAggregations().get("agg1");

        Histogram histogram = searchResponse.getAggregations().get("agg2");

        System.out.println(terms);
        System.out.println(histogram);

        SearchResponse response = transportClient.prepareSearch("bank").setTerminateAfter(1000).get();
        if (response.isTerminatedEarly()){
            System.out.println(response);
        }


    }

}
