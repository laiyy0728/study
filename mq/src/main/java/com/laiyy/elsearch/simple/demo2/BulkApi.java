package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.Date;

/**
 * @author laiyy
 * @date 2018/8/15 17:26
 * @description
 */
public class BulkApi {

    public static void main(String[] args) throws IOException {
        TransportClient transportClient = TransportClientUtils.buildClient();

        BulkRequestBuilder requestBuilder = transportClient.prepareBulk();

        // 可以使用 prepare 创建索引请求
        requestBuilder.add(transportClient.prepareIndex("twitter", "tweet", "1")
            .setSource(XContentFactory.jsonBuilder().startObject()
                .field("user","kimchy")
                .field("postDate", new Date())
                .field("message", "prepare")
            .endObject()));

        // 使用构建 Request 创建索引请求
        IndexRequest indexRequest = new IndexRequest("twitter", "tweet", "2")
                .source(XContentFactory.jsonBuilder().startObject()
                        .field("user", "laiyy")
                        .field("postDate", new Date())
                        .field("message", "request")
                        .endObject());
        requestBuilder.add(indexRequest);

        BulkResponse response = requestBuilder.get();
        if (response.hasFailures()) {
            System.out.println("出错了！！");
        } else {
            BulkItemResponse[] items = response.getItems();
            for (BulkItemResponse item : items) {
                System.err.println(item.getResponse());
            }
        }
    }

}
