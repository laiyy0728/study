package com.laiyy.mybatisboot.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.laiyy.mybatisboot.model.User;
import com.laiyy.mybatisboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author laiyy
 * @date 2018/8/20 16:30
 * @description
 */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("test")
   public void test(){
       userService.selectListByWrapper(new EntityWrapper().like("name", "三")).forEach(System.err::println);

        System.out.println("===========================");
       userService.selectListBySQL().forEach(System.err::println);

        System.out.println("========================");

        Page<User> userPage = userService.selectPage(new Page<>(1, 10));
        System.out.println(userPage.getRecords());

    }

}
