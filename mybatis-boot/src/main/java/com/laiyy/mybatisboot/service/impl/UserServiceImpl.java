package com.laiyy.mybatisboot.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.laiyy.mybatisboot.mapper.UserMapper;
import com.laiyy.mybatisboot.model.User;
import com.laiyy.mybatisboot.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public boolean deleteAll() {
		return retBool(baseMapper.deleteAll());
	}

	@Override
	public List<User> selectListBySQL() {
		return baseMapper.selectListBySql();
	}

	@Override
	public List<User> selectListByWrapper(Wrapper wrapper) {
		return baseMapper.selectListByMapper(wrapper);
	}
}