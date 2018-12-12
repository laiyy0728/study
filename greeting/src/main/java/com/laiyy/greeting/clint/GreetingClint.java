package com.laiyy.greeting.clint;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author laiyy
 * @date 2018/12/11 11:28
 * @description
 */
@FeignClient(serviceId = "greeting-service")
public interface GreetingClint {

    @RequestMapping(method = RequestMethod.GET, value = "greet/{name}")
    Map<String,String> greet(@PathVariable String name);

}
