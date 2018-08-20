package com.laiyy.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.laiyy.mybatis.plus.mapper.UserMapper;
import com.laiyy.mybatis.plus.model.User;
import com.laiyy.mybatis.plus.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author laiyy
 * @date 2018/8/20 11:22
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}
