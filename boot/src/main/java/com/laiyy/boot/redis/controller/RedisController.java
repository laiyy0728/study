package com.laiyy.boot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyy
 * @date 2018/8/29 16:52
 * @description
 */
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate template;

    @GetMapping(value = "set/{key}/{value}")
    public String set(@PathVariable String key, @PathVariable String value){
        template.opsForValue().set(key, value);
        return key + " ---> " + value;
    }

    @GetMapping(value = "get/{key}")
    public String get(@PathVariable String key) {
        return "key = " + key + ", value = " + template.opsForValue().get(key);
    }

}
