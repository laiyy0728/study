package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.script.Script;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author laiyy
 * @date 2018/8/15 16:03
 * @description
 */
public class Update {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        TransportClient transportClient = TransportClientUtils.buildClient();

        // 1、使用 updateRequest
        UpdateRequest request= new UpdateRequest();
        // 设置 index
        request.index("bank");
        // 设置类型
        request.type("account");
        // 设置 id
        request.id("400");
        request.doc(XContentFactory.jsonBuilder().startObject()
                // 设置需要更改的字段，字段值
                .field("firstnname","laiyy")
                .endObject());

        UpdateResponse response = transportClient.update(request).get();
        System.err.println(response);

        // 2、使用 prepareUpdate
//        transportClient.prepareUpdate("bank", "account", "400")
//                .setScript(new Script(""))
    }

}
