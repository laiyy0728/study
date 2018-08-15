package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author laiyy
 * @date 2018/8/14 16:37
 * @description
 */
public class Get {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();
        GetResponse response = transportClient.prepareGet("user", "_doc", "lmOPN2UBIAl5witzIIXf").get();

        System.err.println(response);
    }

}
