package com.laiyy.jetcache.service;

import com.laiyy.jetcache.model.TestModel;

import java.util.List;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/12/4 15:09
 * @description
 */
public interface TestService {

    List<TestModel> listAll(Map<String, Integer> params);

    TestModel getOne(int id);

    void save(TestModel model);

    void update(TestModel model);

    void delete(int id);

}
