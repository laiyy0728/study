package com.laiyy.mybatisboot.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.laiyy.mybatisboot.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author laiyy
 * @date 2018/8/20 16:24
 * @description
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义注入方法
     */
    int deleteAll();

    /**
     * 直接执行 SQL
     */
    @Select("select test_id as id, name, age, test_type from user")
    List<User> selectListBySql();

    List<User> selectListByMapper(@Param("ew")Wrapper wrapper);
}
