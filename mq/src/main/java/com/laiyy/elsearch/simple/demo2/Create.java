package com.laiyy.elsearch.simple.demo2;

import com.alibaba.fastjson.JSON;
import com.laiyy.elsearch.simple.TransportClientUtils;
import com.laiyy.elsearch.simple.User;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/8/14 15:47
 * @description
 */
public class Create {

    private static final Logger LOGGER = LoggerFactory.getLogger(Create.class);

    public static void main(String[] args) {
        TransportClient client = TransportClientUtils.buildClient();

        // 手动写的 JSON
        String json = "{\n" +
                "    \"user\": \"laiyy\",\n" +
                "    \"postDate\": \"2018-08-14\",\n" +
                "    \"message\": \"learning elasticsearch\"\n" +
                "}";


        // 1、发送手动 json，设置 index 为 user，类型为 _doc，类型为 XContentType.JSON
        IndexResponse response = client.prepareIndex("user", "_doc").setSource(json, XContentType.JSON).get();
        System.out.println(String.format("index: %s, type: %s, id: %s, status: %s", response.getIndex(), response.getType(), response.getId(), response.status()));


        // 2、使用 elasticsearch 的 jsonBuilder 来创建 json

        XContentBuilder builder = null;
        try {
            builder = XContentFactory.jsonBuilder().startObject()
                    .field("user", "laiyy")
                    .field("postDate", new Date())
                    .field("message", "message")
                    .endObject();

            // 查看生成的 JSON 内容
            System.out.println(Strings.toString(builder));
        } catch (IOException e) {
            e.printStackTrace();
        }

        response = client.prepareIndex("user", "_doc").setSource(builder).get();

        System.out.println(String.format("index: %s, type: %s, id: %s, status: %s", response.getIndex(), response.getType(), response.getId(), response.status()));

        // 3、使用 map 创建 json
        Map<String, Object> params = new HashMap<>();
        params.put("user", "laiyy1");
        params.put("postDate", new Date());
        params.put("message", "use map");
        response = client.prepareIndex("user","_doc").setSource(params).get();

        System.out.println(String.format("index: %s, type: %s, id: %s, status: %s", response.getIndex(), response.getType(), response.getId(), response.status()));

        // 4、使用序列化 bean
        User user = new User("laiyy2", new Date(), "use java bean");
        byte[] bytes = JSON.toJSONBytes(user);
        try {
            response = client.prepareIndex("user","_doc").setSource(new String(bytes,"UTF-8"), XContentType.JSON).get();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("index: %s, type: %s, id: %s, status: %s", response.getIndex(), response.getType(), response.getId(), response.status()));

    }

}
