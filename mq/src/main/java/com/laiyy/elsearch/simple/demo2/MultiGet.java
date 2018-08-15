package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author laiyy
 * @date 2018/8/15 17:08
 * @description
 */
public class MultiGet {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();

        MultiGetResponse responses = transportClient.prepareMultiGet()
                // 从 index 索引里面获取 id 为 1 的数据
                .add("index", "type", "1")
                // 从 bank 索引里面获取 id 为 400、500、600 的数据
                .add("bank", "account", "400", "500", "600")
                .get();

        // 遍历结果集，打印遍历结果
        for (MultiGetItemResponse multiGetItemResponse : responses) {
            GetResponse getResponse = multiGetItemResponse.getResponse();
            if (getResponse.isExists()){
                System.err.println(getResponse);
            }
        }
    }

}
