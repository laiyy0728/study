package com.laiyy.elsearch.rest.demo1;

import com.laiyy.elsearch.rest.RestClientBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;

import java.util.Collections;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/8/16 16:47
 * @description
 */
public class Demo1 {

    public static void main(String[] args) throws Exception {

        RestClient restClient = RestClientBuilder.restClient();
//        // 设置为美观形式（pretty 为 true）
//        Map<String, String> params = Collections.singletonMap("pretty","true");
//        // 以 GET 方式请求根节点
//        Response response = restClient.performRequest("GET", "/", params);
//        // 请求结果
//        System.out.println(response);


        // 构建索引
//        Map<String, String> params = Collections.emptyMap();
//        String json = "{" +
//                "    \"user\": \"laiyy\"," +
//                "    \"postDate\": \"2018-08-16\"," +
//                "    \"message\": \"REST API PUT DEMO\"" +
//                "}";
//
//        // 设置请求体、请求的 Content-Type
//        HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
//        // 使用 PUT 方式构建索引，索引名称为 posts，索引类型为 doc，索引 id 为 1
//        Response response = restClient.performRequest("PUT", "/posts/doc/1", params, entity);
//        System.out.println(response);

//        Map<String, String> params = Collections.emptyMap();
//        HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory factory =
//                // 设置缓冲区
//                new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024);
//        // 请求方式：GET， 索引：posts，请求schema：_search
//        Response response = restClient.performRequest("GET", "/posts/_search", params, null, factory);
//        System.err.println(response);
//

        ResponseListener listener = new ResponseListener() {
            @Override
            public void onSuccess(Response response) {
                // 请求成功
                System.err.println(response);
            }

            @Override
            public void onFailure(Exception e) {
                // 请求失败
                System.err.println(e.getLocalizedMessage());
            }
        };

        String json = "{" +
                "    \"user\": \"laiyy\"," +
                "    \"postDate\": \"2018-08-16\"," +
                "    \"message\": \"REST API PUT DEMO\"" +
                "}";
//
//        // 设置请求体、请求的 Content-Type
        HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
        Map<String, String> params = Collections.singletonMap("pretty","true");
        restClient.performRequestAsync("PUT", "/posts/doc/2", params, entity, listener);

//        restClient.performRequestAsync("GET", "/", listener);






        restClient.close();

    }

}
