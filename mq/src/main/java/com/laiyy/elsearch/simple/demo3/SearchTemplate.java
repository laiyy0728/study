package com.laiyy.elsearch.simple.demo3;

import com.google.common.collect.Maps;
import com.laiyy.elsearch.simple.TransportClientUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequestBuilder;

import java.util.Map;

/**
 * @author laiyy
 * @date 2018/8/16 16:13
 * @description
 */
public class SearchTemplate {

    public static void main(String[] args) {

        TransportClient transportClient = TransportClientUtils.buildClient();

        Map<String, Object> templateParams = Maps.newHashMap();
        templateParams.put("gender", "male");

        SearchResponse response = new SearchTemplateRequestBuilder(transportClient)
                .setScript("template_gender")
                .setScriptType(ScriptType.STORED)
                .setScriptParams(templateParams)
                .setRequest(new SearchRequest())
                .get().getResponse();

        System.err.println(response);
    }

}
