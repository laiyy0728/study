package com.laiyy.discovery.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/12/11 10:44
 * @description
 */
@Profile({"default", "insecure"})
@RestController
@RequestMapping(value = "/greet/{name}", method = RequestMethod.GET)
public class DefaultGreetingRestController {
    @RequestMapping
    public Map<String, String> hi(@PathVariable String name) {
        return Collections.singletonMap("greeting", "hello, " + name + " !");
    }
}
