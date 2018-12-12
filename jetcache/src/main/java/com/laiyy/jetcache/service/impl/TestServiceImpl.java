package com.laiyy.jetcache.service.impl;

import com.alicp.jetcache.anno.Cached;
import com.laiyy.jetcache.model.TestModel;
import com.laiyy.jetcache.service.TestService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author laiyy
 * @date 2018/12/4 15:10
 * @description
 */
@Component
public class TestServiceImpl implements TestService {

    private static List<TestModel> models;
    static {
        models  = new ArrayList<>();
        models.add(new TestModel(1, "张三"));
        models.add(new TestModel(2, "李四"));
        models.add(new TestModel(3, "王五"));
        models.add(new TestModel(4, "赵六"));
        models.add(new TestModel(5, "田七"));
        models.add(new TestModel(6, "张麻子"));
        models.add(new TestModel(7, "二傻子"));
    }

    @Override
    @Cached(name = "cms:news:list:all", expire = 20, key = "#params")
    public List<TestModel> listAll(Map<String, Integer> params) {
        System.out.println("没有走缓存！！！");
        return models;
    }

    @Override
    public TestModel getOne(int id) {
        TestModel model = null;
        for (TestModel testModel : models) {
            if (testModel.getId() == id){
                model = testModel;
            }
        }
        return model;
    }

    @Override
    public void save(TestModel model) {
        models.add(model);
    }

    @Override
    public void update(TestModel model) {

    }

    @Override
    public void delete(int id) {

    }
}
