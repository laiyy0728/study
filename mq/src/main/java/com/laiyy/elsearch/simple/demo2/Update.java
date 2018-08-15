package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.index.IndexRequest;
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
        UpdateRequest request = new UpdateRequest();
        // 设置 index
        request.index("bank");
        // 设置类型
        request.type("account");
        // 设置 id
        request.id("400");
        request.doc(XContentFactory.jsonBuilder().startObject()
                // 设置需要更改的字段，字段值
                .field("firstnname", "laiyy")
                .endObject());

        UpdateResponse response = transportClient.update(request).get();
        System.err.println(response);

        // 2、使用 prepareUpdate$setScript
        response = transportClient.prepareUpdate("bank", "account", "400")
                .setScript(new Script("ctx._source.firstname=\"laiyy\"")).get();

        System.err.println(response);

        // 3、使用 prepareUpdate$setDoc
        response = transportClient.prepareUpdate("bank", "account", "400")
                .setDoc(XContentFactory.jsonBuilder().startObject()
                        .field("firstname", "lyl")
                        .endObject()).get();
        System.err.println(response);

        // 4、在 UpdateRequest 中使用脚本更新（script）
        request = new UpdateRequest("bank", "account", "400")
                .script(new Script("ctx._source.lastname=\"laiyy\""));
        response = transportClient.update(request).get();
        System.err.println(response);

        // 5、在 UpdateRequest 中使用文档更新（doc）
        request = new UpdateRequest("bank", "account", "400")
                .doc(XContentFactory.jsonBuilder().startObject().field("balance", 100).endObject());
        response = transportClient.update(request).get();
        System.err.println(response);

        // 6、使用 Upsert 更新文档（如果文档不存在则会创建索引）
        // 首先，创建一个 IndexRequest，用于在更新时没有索引自动创建
        IndexRequest indexRequest = new IndexRequest("index", "type", "1")
                .source(XContentFactory.jsonBuilder().startObject()
                        .field("name", "laiyy")
                        .field("gender", "male")
                        .endObject());
        // 其次，创建 UpdateRequest，进行更新操作
        request = new UpdateRequest("index", "type", "1")
                .doc(XContentFactory.jsonBuilder().startObject()
                        .field("name", "lyl")
                        .endObject())
                // 添加索引请求，此方法会在没有索引时自动创建，有索引时会忽略
                .upsert(indexRequest);
        response = transportClient.update(request).get();
        System.err.println(response);
    }

}
