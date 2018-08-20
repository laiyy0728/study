package com.laiyy.mybatisboot.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.laiyy.mybatisboot.model.User;

import java.util.List;

public interface IUserService extends IService<User> {

	boolean deleteAll();

	public List<User> selectListBySQL();

	public List<User> selectListByWrapper(Wrapper wrapper);
}