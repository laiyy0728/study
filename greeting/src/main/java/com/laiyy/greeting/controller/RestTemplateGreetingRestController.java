//package com.laiyy.greeting.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
///**
// * @author laiyy
// * @date 2018/12/11 10:57
// * @description
// */
//@RestController
//@RequestMapping("/api")
//public class RestTemplateGreetingRestController {
//
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public RestTemplateGreetingRestController(@LoadBalanced RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @GetMapping(value = "/resttemplate/{name}")
//    public Map<String, String> restTemplate(@PathVariable String name){
//        ParameterizedTypeReference<Map<String, String>> reference = new ParameterizedTypeReference<Map<String, String>>() {};
//
//        ResponseEntity<Map<String, String>> responseEntity = this.restTemplate.exchange("http://greeting-service/greet/{name}", HttpMethod.GET, null, reference, name);
//        return responseEntity.getBody();
//    }
//
//}
