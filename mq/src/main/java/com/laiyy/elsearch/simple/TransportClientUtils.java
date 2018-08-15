package com.laiyy.elsearch.simple;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author laiyy
 * @date 2018/8/14 15:48
 * @description
 */
public class TransportClientUtils {

    /**
     * 设置 elasticsearch ip
     */
    private static final String HOST = "192.168.67.129";

    /**
     * 使用 API 访问的端口为 9300，使用 HTTP 访问的端口为 9200
     */
    private static final int PORT = 9300;

    public static TransportClient buildClient(){
        // 根据 HOST、POST 获取地址
        TransportAddress transportAddress = null;
        try {
            transportAddress = new TransportAddress(InetAddress.getByName(HOST), PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // 采用空设置 elasticsearch（即默认设置）

        // 设置 cluster.name
        Settings settings = Settings.builder().put("cluster.name", "laiyy")
                // 启用嗅探
                .put("client.transport.sniff", true)
                .build();

        return new PreBuiltTransportClient(settings).addTransportAddress(transportAddress);
    }

}
