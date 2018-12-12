package com.laiyy.jetcache.controller;

import com.google.common.collect.Maps;
import com.laiyy.jetcache.model.TestModel;
import com.laiyy.jetcache.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/12/4 10:44
 * @description
 */
@RestController
public class NewsController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "test")
    public void test(){
        System.out.println("测试缓存");
        Map<String, Integer> params = Maps.newHashMap();
        params.put("pageNumber", 0);
        params.put("pageSize", 1);
        List<TestModel> models = testService.listAll(params);
        models.forEach(System.err::println);

    }
}
