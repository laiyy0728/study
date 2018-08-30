package com.laiyy.boot.redis.controller;

import com.laiyy.boot.redis.dto.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyy
 * @date 2018/8/29 17:22
 * @description
 */
@RestController
public class SpringCacheController {

    @PostMapping(value = "add")
    public User addUser(User user){
        user.setId(1);
        return user;
    }

    @GetMapping(value = "user/{id}")
    @Cacheable(value = "user", key = "#id")
    public User getUser(@PathVariable int id){
        User user = new User();
        user.setId(id);
        user.setCode("6666");
        user.setName("laiyy");
        return user;
    }

}
