package com.laiyy.mybatis.plus.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.laiyy.mybatis.plus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laiyy
 * @date 2018/8/20 11:25
 * @description
 */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value ="test")
    public void test(){
        System.out.println(userService.selectById(784972358981328902L));
    }

}
