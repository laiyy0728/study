package com.laiyy.elsearch.simple.demo2;

import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.transport.TransportClient;

/**
 * @author laiyy
 * @date 2018/8/14 16:41
 * @description
 */
public class Delete {

    public static void main(String[] args) {
        TransportClient transportClient = TransportClientUtils.buildClient();
        DeleteResponse response = transportClient.prepareDelete("user", "_doc", "lmOPN2UBIAl5witzIIXf").get();
        System.err.println(response);
    }

}
