package com.laiyy.elsearch.rest;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;

/**
 * @author laiyy
 * @date 2018/8/16 16:29
 * @description
 */
public class RestClientBuilder {

    public static RestClient restClient() {
        org.elasticsearch.client.RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("192.168.67.129", 9200, "http"));
        // 超时重试时间 10 秒
        clientBuilder.setMaxRetryTimeoutMillis(10000);
        // 出错提示
        clientBuilder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(HttpHost host) {
                System.out.println("出错了，地址：" + host.toHostString());
            }
        });
        // 设置请求超时时间
        clientBuilder.setRequestConfigCallback(new org.elasticsearch.client.RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                return builder.setSocketTimeout(10000);
            }
        });

        // 设置 http 请求代理
//        clientBuilder.setHttpClientConfigCallback(new org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback() {
//            @Override
//            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
//                return httpAsyncClientBuilder.setProxy(new HttpHost("proxy_host", 9000, "http"));
//            }
//        })

        return clientBuilder.build();
    }

}
