package com.laiyy.greeting.clint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author laiyy
 * @date 2018/12/11 11:33
 * @description
 */
//@Profile("feign")
@RestController
@RequestMapping(value = "api")
public class FeignGreetingClientApiController {

    @Autowired
    private GreetingClint greetingClint;

    @GetMapping(value = "feign/{name}")
    public Map<String, String> feign(@PathVariable String name){
        return greetingClint.greet(name);
    }

}
